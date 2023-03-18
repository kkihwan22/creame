package today.creame.web.member.application;

import today.creame.web.member.application.model.ForgetEmailParameter;
import today.creame.web.member.application.model.ForgetPasswordParameter;
import today.creame.web.member.application.model.MemberExpireParameter;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.application.model.MemberUpdateParameter;
import today.creame.web.member.application.model.NotificationSettingParameter;

public interface MemberService {
    MemberResult registerMember(MemberRegisterParameter parameter);

    MemberResult registerMemberInfluence(MemberRegisterParameter parameter);

    String forgetEmail(ForgetEmailParameter parameter);

    void forgetPassword(ForgetPasswordParameter parameter);

    void updateNickname(MemberUpdateParameter parameter);

    void updatePassword(MemberUpdateParameter parameter);

    void expire(MemberExpireParameter parameter);

    void changedNotificationCondition(NotificationSettingParameter parameter);

    void changedPhoneNumber(Long token, String phoneNumber);
}