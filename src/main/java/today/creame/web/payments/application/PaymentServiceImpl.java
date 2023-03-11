package today.creame.web.payments.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.m2net.infra.feign.M2netClient;
import today.creame.web.m2net.infra.feign.M2netUserClient;
import today.creame.web.m2net.infra.feign.io.M2netBillKeyIssueResponse;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.payments.application.model.CreditCardResult;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.payments.domain.PaymentBillKey;
import today.creame.web.payments.domain.PaymentBillKeyJpaRepository;
import today.creame.web.payments.exception.ConflictCreditCardException;
import today.creame.web.payments.exception.IllegalCreditCardDataException;
import today.creame.web.payments.exception.IssueBillKeyException;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {
    private final Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final IssueBillKeyMapper mapper;
    private final M2netClient m2netClient;
    private final M2netUserClient m2netUserClient;
    private final PaymentBillKeyJpaRepository paymentBillKeyJpaRepository;

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

        try {
            M2netBillKeyIssueResponse body = m2netClient.issueBillKey(mapper.mapping(creditCard, member)).getBody();
            if (!body.getReqResult().equals("00")) {
                log.error("[ error ] code: {}, message: {} ", body.getReqResult(), body.getResultmessage());
                throw new IllegalCreditCardDataException();
            }

            String billKey = body.getBillKey();
            log.debug("발급 된 빌키: {}", billKey);
            paymentBillKeyJpaRepository.save(new PaymentBillKey(member, billKey, creditCard));
        } catch (Exception e) {
            log.error("빌키 발급 중 에러가 발생했습니다.\n error : {}", e);
            throw new IssueBillKeyException();
        }
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

        // boolean exist
        // credit card -> card no ( 8자리 나머지는 xxx )
        // auto charging
    }
}