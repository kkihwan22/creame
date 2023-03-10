package today.creame.web.payments.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReceiptRequest {

    private String oid;
    private String tid;
}
