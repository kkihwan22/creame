package today.creame.web.member.application;

import today.creame.web.member.application.model.*;

import java.util.List;

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
    List<MemberSearchResult> findAllByEmailOrPhoneNumberOrNickname(MemberSearchParameter parameter);
    void memberRoleUpdate(Long memberId);

    MemberDetailResult getDetail(Long memberId);
    void changeMemberInfo(MemberInfoUpdateParameter parameter);
    void changePasswordByAdmin(Long memberId);
}