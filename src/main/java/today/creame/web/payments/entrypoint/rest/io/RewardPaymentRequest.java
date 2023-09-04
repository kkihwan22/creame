package today.creame.web.payments.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class RewardPaymentRequest {
    @NotNull
    private Long memberId;
    @NotNull
    private Integer amount;

}
