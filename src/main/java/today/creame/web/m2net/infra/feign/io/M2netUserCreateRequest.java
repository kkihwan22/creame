package today.creame.web.m2net.infra.feign.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.application.model.M2netUserCreateParameter;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class M2netUserCreateRequest {
    private String membnm;
    private String telno;
    private int amt;
    private String autopayflag;

    public M2netUserCreateRequest(M2netUserCreateParameter parameter) {
        this.membnm = parameter.getNickname();
        this.telno = parameter.getPhoneNumber();
        this.amt = 0;
    }
}