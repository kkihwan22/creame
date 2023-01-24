package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.application.model.M2netUserCreateParameter;

@Getter
@ToString
public class M2netUserCreateRequest {
    private String membnm;
    private String telno;
    private int amt;

    public M2netUserCreateRequest(M2netUserCreateParameter parameter) {
        this.membnm = parameter.getNickname();
        this.telno = parameter.getPhoneNumber();
        this.amt = 0;
    }
}