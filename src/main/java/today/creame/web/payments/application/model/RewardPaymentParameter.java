package today.creame.web.payments.application.model;

import lombok.Getter;
import today.creame.web.payments.entrypoint.rest.io.RewardPaymentRequest;

@Getter
public class RewardPaymentParameter {
    private Long memberId;
    private Integer amount;

    public RewardPaymentParameter(RewardPaymentRequest request) {
        this.memberId = request.getMemberId();
        this.amount = request.getAmount();

    }
}
