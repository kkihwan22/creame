package today.creame.web.payments.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.payments.application.model.PaymentFailureParameter;
import today.creame.web.payments.application.model.PaymentSuccessParameter;
import today.creame.web.payments.domain.PaymentMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ReceiptRequest {
    private static final Map<String, PaymentMethod> dataMap = Map.of(
            "KAKAO_PAY", PaymentMethod.KAKAO,
            "NAVER_PAY", PaymentMethod.NAVER,
            "PAYCO_PAY", PaymentMethod.PAYCO,
            "DIR_CARD", PaymentMethod.CARD
    );

    private String membid;
    private String membnm;
    private String oid;
    private String tid;
    private int amount;
    private int coinamt;
    private String payType;
    private String reqResult;
    private String resultmessage;

    public PaymentSuccessParameter toSuccess() {



        return new PaymentSuccessParameter(membid, oid, tid, amount, coinamt, ReceiptRequest.dataMap.get(Optional.ofNullable(payType).orElse("DIR_CARD")));
    }

    public PaymentFailureParameter toFailed() {
        return new PaymentFailureParameter(membid, oid, tid, amount, coinamt, ReceiptRequest.dataMap.get(payType), reqResult, resultmessage);
    }
}
