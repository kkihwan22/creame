package today.creame.web.member.application;

import java.util.List;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.application.model.MyQuestionsQueryParameter;

public interface MemberQuery {
    MeResult getMe(Long id);

    MemberResult getId(Long id);

    List<InfluenceQnaResult> pagingListMyQuestions(MyQuestionsQueryParameter parameter);

    boolean existMemberByNickname(String nickname);
    boolean existMemberByEmail(String email);
    boolean existMemberByPhoneNumber(String phoneNumber);
}
