package today.creame.web.coin.entrypoint.rest.io;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PaymentDetailResponse {
    private Long id;
    private String type;
    private String method;
    private int amount;
    private LocalDateTime paymentDateTime;

    public PaymentDetailResponse(Long id, String type, String method, int amount, LocalDateTime paymentDateTime) {
        this.id = id;
        this.type = type;
        this.method = method;
        this.amount = amount;
        this.paymentDateTime = paymentDateTime;
    }
}