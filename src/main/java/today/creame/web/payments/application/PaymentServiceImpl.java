package today.creame.web.payments.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.coin.application.CoinsService;
import today.creame.web.coin.application.model.CoinsUpdateParameter;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.m2net.infra.feign.M2netClient;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueResponse;
import today.creame.web.m2net.infra.feign.io.M2netRemoveBillKeyRequest;
import today.creame.web.m2net.infra.feign.io.M2netRewardRequest;
import today.creame.web.m2net.infra.feign.io.M2netSimpleResponse;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.payments.application.model.CreditCardResult;
import today.creame.web.payments.application.model.PaymentFailureParameter;
import today.creame.web.payments.application.model.PaymentSuccessParameter;
import today.creame.web.payments.application.model.RewardPaymentParameter;
import today.creame.web.payments.domain.*;
import today.creame.web.payments.exception.*;
import today.creame.web.share.event.AutoChargingConfigEvent;
import today.creame.web.share.event.PaymentEvent;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final IssueBillKeyConverter issueBillKeyConverter;
    private final M2netClient m2netClient;
    private final PaymentBillKeyJpaRepository paymentBillKeyJpaRepository;
    private final PaymentHistoryJpaRepository paymentHistoryJpaRepository;
    private final ApplicationEventPublisher publisher;
    private final CoinsService coinsService;

    @Transactional
    @Override
    public void issueBillKey(CreditCard creditCard) {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);

        if (paymentBillKeyJpaRepository.existsPaymentBillKeyByMemberIdAndDeleted(member.getId(), false)) {
            log.error("이미 등록 된 카드가 있습니다.");
            throw new ConflictCreditCardException();
        }

        M2netBillKeyIssueResponse body = m2netClient.issueBillKey(issueBillKeyConverter.convert(creditCard, null, member)).getBody();
        if (!body.getReqResult().equals("00")) {
            log.error("[ error ] code: {}, message: {} ", body.getReqResult(), body.getResultmessage());
            throw new IllegalCreditCardDataException();
        }

        String billKey = body.getBillKey();
        log.debug("발급 된 빌키: {}", billKey);
        paymentBillKeyJpaRepository.save(new PaymentBillKey(member, billKey, creditCard));
    }

    @Transactional
    @Override
    public void removeBillKey() {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);

        PaymentBillKey paymentBillKey = paymentBillKeyJpaRepository
            .findPaymentBillKeyByMemberIdAndDeleted(member.getId(), false)
            .orElseThrow(NotFoundCreditCardException::new);

        this.removeBillKey(member);
        paymentBillKey.delete();
    }

    @Transactional
    @Override
    public void enableAutoCharging(AutoChargingPreference preference) {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);

        PaymentBillKey paymentBillKey = paymentBillKeyJpaRepository
            .findPaymentBillKeyByMemberIdAndDeleted(member.getId(), false)
            .orElseThrow(NotFoundCreditCardException::new);

        paymentBillKey.enabledAutoCharging(preference);
        this.resetBillKey(paymentBillKey, member);
        log.debug("paymentBillKey: {}", paymentBillKey);
        publisher.publishEvent(new AutoChargingConfigEvent(member.getM2netUserId(), "Y"));
    }

    @Transactional
    @Override
    public void disabledAutoCharging() {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);
        publisher.publishEvent(new AutoChargingConfigEvent(member.getM2netUserId(), "N"));
    }

    @Transactional
    @Override
    public void changePaymentPassword(String oPass, String nPass) {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);

        PaymentBillKey paymentBillKey = paymentBillKeyJpaRepository
            .findPaymentBillKeyByMemberIdAndDeleted(member.getId(), false)
            .orElseThrow(NotFoundCreditCardException::new);

        paymentBillKey.changePassword(oPass, nPass);
    }

    @Override
    public CreditCardResult getCreditCard() {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);

        return paymentBillKeyJpaRepository.findPaymentBillKeyByMemberIdAndDeleted(member.getId(), false)
            .map(it -> new CreditCardResult(it.getCreditCard(), it.getPreference()))
            .orElse(null);
    }

    @Override
    public void payByBillKey(String paymentPassword, int amount) {
        Member member = memberJpaRepository
            .findById(SecurityContextSupporter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("member: {}", member);

        PaymentBillKey paymentBillKey = paymentBillKeyJpaRepository
            .findPaymentBillKeyByMemberIdAndDeleted(member.getId(), false)
            .orElseThrow(NotFoundCreditCardException::new);
        try {
            paymentBillKey.pay(paymentPassword, amount, member, m2netClient);
        } catch (PaymentFailureException e) {
            log.error("결제에 실패하였습니다.");
            throw e;
        }
    }

    private void resetBillKey(PaymentBillKey paymentBillKey, Member member) {
        removeBillKey(member);
        M2netBillKeyIssueResponse body = m2netClient.issueBillKey(issueBillKeyConverter.convert(paymentBillKey.getCreditCard(), paymentBillKey.getPreference(), member)).getBody();
        if (!body.getReqResult().equals("00")) {
            log.error("[ error ] code: {}, message: {} ", body.getReqResult(), body.getResultmessage());
            throw new IllegalCreditCardDataException();
        }
        paymentBillKey.updateBillKey(body.getBillKey());
    }

    private void removeBillKey(Member member) {
        M2netSimpleResponse body = m2netClient.removeBillKey(new M2netRemoveBillKeyRequest(member.getM2netUserId())).getBody();
        if (!body.getReqResult().equals("00")) {
            log.error("빌키 삭제 중 에러가 발생했습니다.");
            throw new RemoveBillKeyException();
        }
    }

    @Transactional
    @Override
    public void paySuccess(PaymentsHistoryStatus type, PaymentSuccessParameter parameter) {

        paymentHistoryJpaRepository.findByOrderId(parameter.getOid())
                .ifPresentOrElse(
                        it -> {
                            it.canceled();
                            this.publishPaymentResult(it);
                        },
                        () -> {
                            Member member = memberJpaRepository.findMemberByM2netUserId(parameter.getMembid()).orElseThrow(NotFoundMemberException::new);
                            PaymentsHistory paymentsHistory = paymentHistoryJpaRepository.save(new PaymentsHistory(member, type, parameter.getOid(), parameter.getTid(), parameter.getAmount(), parameter.getCoinamt(), parameter.getPaymentMethod()));
                            this.publishPaymentResult(paymentsHistory);
                        }
                );
    }

    @Transactional
    @Override
    public void payFailure(PaymentFailureParameter parameter) {
        Member member = memberJpaRepository.findMemberByM2netUserId(parameter.getMembid()).orElseThrow(NotFoundMemberException::new);
        paymentHistoryJpaRepository.save(new PaymentsHistory(member, PaymentsHistoryStatus.FAILED, parameter.getOid(), parameter.getTid(), parameter.getAmount(), parameter.getCoinamt(), parameter.getPaymentMethod(), parameter.getReqResult(), parameter.getResultmessage()));
    }

    @Override
    public void payByReward(RewardPaymentParameter parameter) {
        Member member = memberJpaRepository
                .findById(parameter.getMemberId())
                .orElseThrow(NotFoundMemberException::new);
        m2netClient.payByReward(member.getM2netUserId(), new M2netRewardRequest(parameter.getAmount()));

        CoinsUpdateParameter coinsUpdateParameter = new CoinsUpdateParameter(member.getId(), parameter.getAmount(), CoinsHistoryType.REWARD);
        coinsService.update(coinsUpdateParameter);
    }

    private void publishPaymentResult(PaymentsHistory paymentsHistory) {
        publisher.publishEvent(new PaymentEvent(paymentsHistory));
    }
}