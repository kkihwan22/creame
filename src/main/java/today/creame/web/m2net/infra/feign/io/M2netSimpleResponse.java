package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netSimpleResponse {
    private String reqResult;
    private String resultmessage;

    public M2netSimpleResponse(String reqResult, String resultmessage) {
        this.reqResult = reqResult;
        this.resultmessage = resultmessage;
    }
}
