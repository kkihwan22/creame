package today.creame.web.members.applications;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.members.applications.model.MemberRegisterParameter;
import today.creame.web.members.domain.Member;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Override
    public void registerMember(MemberRegisterParameter parameter) {

        Member createdMember = new Member(
                null, parameter.getEmail(), parameter.getNickname(), parameter.getPassword(), parameter.getPhoneNumber()
        );
    }
}
