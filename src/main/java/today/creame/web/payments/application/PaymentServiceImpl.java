package today.creame.web.payments.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.m2net.infra.feign.M2netClient;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueResponse;
import today.creame.web.m2net.infra.feign.io.M2netRemoveBillKeyRequest;
import today.creame.web.m2net.infra.feign.io.M2netSimpleResponse;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.payments.application.model.CreditCardResult;
import today.creame.web.payments.application.model.ReceiptParameter;
import today.creame.web.payments.domain.AutoChargingPreference;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.payments.domain.PaymentBillKey;
import today.creame.web.payments.domain.PaymentBillKeyJpaRepository;
import today.creame.web.payments.domain.PaymentHistoryJpaRepository;
import today.creame.web.payments.domain.PaymentsHistory;
import today.creame.web.payments.exception.ConflictCreditCardException;
import today.creame.web.payments.exception.IllegalCreditCardDataException;
import today.creame.web.payments.exception.NotFoundCreditCardException;
import today.creame.web.payments.exception.PaymentFailureException;
import today.creame.web.payments.exception.RemoveBillKeyException;
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

    @Transactional
    @Override
    public void postPay(ReceiptParameter parameter) {
        PaymentsHistory paymentsHistory = parameter.toEntity(memberJpaRepository);
        paymentHistoryJpaRepository.save(paymentsHistory);
        publisher.publishEvent(new PaymentEvent(
            paymentsHistory.getMember().getId(), paymentsHistory.getType(), parameter.getAmount(), parameter.getCoinamt()));
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


}