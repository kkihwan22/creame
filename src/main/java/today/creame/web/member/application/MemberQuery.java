package today.creame.web.member.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.application.model.MemberListSearchParameter;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.application.model.MemberSearchParameter;
import today.creame.web.member.domain.MemberNotificationPreference;
import today.creame.web.member.entrypoint.rest.io.MemberListResponse;

public interface MemberQuery {
    MeResult getMe(Long id);

    MemberResult getId(Long id);

    boolean existMemberByNickname(String nickname);

    boolean existMemberByEmail(String email);

    boolean existMemberByPhoneNumber(String phoneNumber);

    MemberNotificationPreference getNotificationSetting(Long id);

    Page<MemberListResponse> getList(Pageable pageable, MemberListSearchParameter parameter);

    String getDuplicates(Long influenceApplicationId, MemberSearchParameter parameter);

}
