package today.creame.web.member.application;

import today.creame.web.member.application.model.MemberRegisterParameter;

public interface MemberService {

    Long registerMember(MemberRegisterParameter parameter);
}
