package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netRemoveBillKeyRequest {
    private String membid;

    public M2netRemoveBillKeyRequest(String membid) {
        this.membid = membid;
    }
}
