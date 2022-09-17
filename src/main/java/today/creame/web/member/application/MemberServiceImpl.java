package today.creame.web.member.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.member.domain.*;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;

    @Transactional
    @Override
    public Long registerMember(MemberRegisterParameter parameter) {
        // 인증여부 확인.!
        // phone 번호 등록 수정

        Member registerMember = parameter.toMember();
        MemberRole role = new MemberRole(null, MemberRoleCode.USER);
        registerMember.addRole(role);
        memberJpaRepository.save(registerMember);

        log.debug("register member: {}", registerMember);
        log.debug("roles: {}", registerMember.getRoles());

        return registerMember.getId();
    }
}
