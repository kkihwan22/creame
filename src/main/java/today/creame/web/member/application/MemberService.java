package today.creame.web.member.application;

import today.creame.web.member.application.model.EmailLossParameter;
import today.creame.web.member.application.model.MemberExpireParameter;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.member.application.model.MemberUpdateParameter;
import today.creame.web.member.application.model.NotificationSettingParameter;
import today.creame.web.member.application.model.PasswordParameter;

public interface MemberService {
    Long registerMember(MemberRegisterParameter parameter);

    Long registerMemberInfluence(MemberRegisterParameter parameter);

    String emailLoss(EmailLossParameter parameter);

    void passwordLoss(PasswordParameter parameter);

    void changeNickname(MemberUpdateParameter parameter);

    void changePassword(MemberUpdateParameter parameter);

    void expire(MemberExpireParameter parameter);

    void changedNotificationSetting(NotificationSettingParameter parameter);
}

