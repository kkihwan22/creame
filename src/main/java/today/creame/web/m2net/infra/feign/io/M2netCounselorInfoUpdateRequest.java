package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import today.creame.web.influence.domain.Influence;

@Getter
public class M2netCounselorInfoUpdateRequest {
    private String csrnm;
    private String telno;

    public M2netCounselorInfoUpdateRequest(Influence influence) {
        this.csrnm = influence.getNickname();
        this.telno = influence.getPhoneNumber();
    }

}
