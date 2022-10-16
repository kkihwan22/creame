package today.creame.web.member.application;

import today.creame.web.member.application.model.MeResult;

public interface MemberQuery {

    MeResult getMe(Long id);

}
