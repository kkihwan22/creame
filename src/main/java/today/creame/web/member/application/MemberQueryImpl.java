package today.creame.web.member.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;
import today.creame.web.member.exception.NotFoundMemberException;

@RequiredArgsConstructor
@Component
public class MemberQueryImpl implements MemberQuery {
    private final Logger log = LoggerFactory.getLogger(MemberQueryImpl.class);
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public MeResult getMe(Long id) {
        Member findMember = memberJpaRepository.findById(id)
            .orElseThrow(NotFoundMemberException::new);

        log.debug("find member: {}", findMember);

        List<MemberRoleCode> roles = findMember.getRoles()
            .stream().map(MemberRole::getCodeName)
            .collect(Collectors.toList());

        return new MeResult(id, findMember.getNickname(), roles);
    }

    @Override
    public boolean existMemberByNickname(String nickname) {
        Long count = memberJpaRepository.countMemberByNickname(nickname);
        return count > 0;
    }

    @Override
    public boolean existMemberByEmail(String email) {
        Long count = memberJpaRepository.countMemberByEmail(email);
        return count > 0;
    }

    @Override
    public boolean existMemberByPhoneNumber(String phoneNumber) {
        Long count = memberJpaRepository.countMemberByPhoneNumber(phoneNumber);
        return count > 0;
    }
}
