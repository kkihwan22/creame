package today.creame.web.member.application;

import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.domain.MemberNotificationPreference;

public interface MemberQuery {
    MeResult getMe(Long id);

    MemberResult getId(Long id);

    boolean existMemberByNickname(String nickname);

    boolean existMemberByEmail(String email);

    boolean existMemberByPhoneNumber(String phoneNumber);

    MemberNotificationPreference getNotificationSetting(Long id);
}
