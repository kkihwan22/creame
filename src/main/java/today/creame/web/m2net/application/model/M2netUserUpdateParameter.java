package today.creame.web.m2net.application.model;

import lombok.Getter;
import today.creame.web.member.domain.Member;

@Getter
public class M2netUserUpdateParameter {
    private String membnm;
    private String telno;

    public M2netUserUpdateParameter(Member member) {
        this.membnm = member.getNickname();
        this.telno = member.getPhoneNumber();
    }
}
