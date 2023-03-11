package today.creame.web.payments.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
public class BillKeyPaymentRequest {

    @NotBlank
    @Length(max = 6, min = 6)
    private String paymentPassword;

    @Positive
    private int amount;

    public BillKeyPaymentRequest(String paymentPassword, int amount) {
        this.paymentPassword = paymentPassword;
        this.amount = amount;
    }
}
