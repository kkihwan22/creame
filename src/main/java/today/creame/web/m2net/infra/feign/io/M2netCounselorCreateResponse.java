package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netCounselorCreateResponse {
    private String reqResult;
    private String resultmessage;
    private String csrid;
}
