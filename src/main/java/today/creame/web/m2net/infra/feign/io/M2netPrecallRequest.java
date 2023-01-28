package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netPrecallRequest {
    private String telno;
    private String csrid;

    public M2netPrecallRequest(String telno, String csrid) {
        this.telno = telno;
        this.csrid = csrid;
    }
}
