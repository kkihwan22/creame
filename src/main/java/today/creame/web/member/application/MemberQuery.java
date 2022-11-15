package today.creame.web.member.application;

import today.creame.web.member.application.model.MeResult;

public interface MemberQuery {

    MeResult getMe(Long id);
    boolean existMemberByNickname(String nickname);
    boolean existMemberByEmail(String email);
    boolean existMemberByPhoneNumber(String phoneNumber);
}
