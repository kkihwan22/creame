package today.creame.web.payments.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentPasswordChangeRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    public PaymentPasswordChangeRequest(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
