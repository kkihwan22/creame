package today.creame.web.member.application;

import today.creame.web.member.application.model.ForgetEmailParameter;
import today.creame.web.member.application.model.ForgetPasswordParameter;
import today.creame.web.member.application.model.MemberExpireParameter;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.member.application.model.MemberUpdateParameter;
import today.creame.web.member.application.model.NotificationSettingParameter;

public interface MemberService {
    Long registerMember(MemberRegisterParameter parameter);

    Long registerMemberInfluence(MemberRegisterParameter parameter);

    String forgetEmail(ForgetEmailParameter parameter);

    void forgetPassword(ForgetPasswordParameter parameter);

    void updateNickname(MemberUpdateParameter parameter);

    void updatePassword(MemberUpdateParameter parameter);

    void expire(MemberExpireParameter parameter);

    void changedNotificationSetting(NotificationSettingParameter parameter);
}