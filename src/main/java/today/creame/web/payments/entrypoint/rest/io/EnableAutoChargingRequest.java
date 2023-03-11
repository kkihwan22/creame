package today.creame.web.payments.entrypoint.rest.io;

import javax.validation.constraints.Min;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EnableAutoChargingRequest {

    @Min(value = 10000)
    private Integer chargingAmount;
}
