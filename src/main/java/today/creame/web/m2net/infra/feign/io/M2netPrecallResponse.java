package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netPrecallResponse {
    private String reqResult;
    private String resultmessage;
    private String telno;
    private String csrid;

    public M2netPrecallResponse(String telno, String csrid) {
        this.telno = telno;
        this.csrid = csrid;
    }
}