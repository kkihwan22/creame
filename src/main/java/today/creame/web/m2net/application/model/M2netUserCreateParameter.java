package today.creame.web.m2net.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netUserCreateParameter {
    private String nickname;
    private String phoneNumber;

    public M2netUserCreateParameter(String nickname, String phoneNumber) {
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }
}
