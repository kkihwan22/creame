package today.creame.web.member.entrypoint.event.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class InfluenceMemberCreatEvent {

    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;

    // influence 특징
    private String introduction;
    private String categories;
    private String profileImages;
}
