package today.creame.web.influence.application.delegate;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.member.application.MemberService;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.share.support.RandomString;

@RequiredArgsConstructor
@Component
public class MemberCreateDelegateImpl implements MemberCreateDelegate {
    private final Logger log = LoggerFactory.getLogger(MemberCreateDelegateImpl.class);
    private final MemberService memberService;

    @Transactional
    @Override
    public Long createMember(InfluenceApplication application) {
        String password = RandomString.generatePassword().nextString();
        log.debug(" [ password]: {}", password);

        Long id = memberService.registerMember(new MemberRegisterParameter(
            application.getEmail(),
            application.getNickname(),
            password,
            application.getPhoneNumber()));

        log.debug("create member id: {}", id);
        return id;
    }
}