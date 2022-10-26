package today.creame.web.member.application;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;
import today.creame.web.member.domain.MemberRoleJpaRepository;
import today.creame.web.member.exception.ConflictMemberEmailException;
import today.creame.web.member.exception.ConflictMemberNicknameException;
import today.creame.web.member.exception.ConflictMemberPhoneNumberException;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final MemberRoleJpaRepository memberRoleJpaRepository;

    @Transactional
    @Override
    public Long registerMember(MemberRegisterParameter parameter) {

        if (memberJpaRepository.countMemberByEmail(parameter.getEmail()) > 0) {
            log.info(" 요청하신 이메일({})은 이미 사용 중입니다.", parameter.getEmail());
            throw new ConflictMemberEmailException();
        }

        if (memberJpaRepository.countMemberByNickname(parameter.getNickname()) > 0) {
            log.info(" 요청하신 닉네임({})은 이미 사용 중입니다.", parameter.getNickname());
            throw new ConflictMemberNicknameException();
        }

        if (memberJpaRepository.countMemberByPhoneNumber(parameter.getPhoneNumber()) > 0) {
            log.info(" 요청하신 휴대전화 번호({})는 이미 사용 중입니다.", parameter.getPhoneNumber());
            throw new ConflictMemberPhoneNumberException();
        }

        Member registerMember = parameter.toEntity();
        memberJpaRepository.save(registerMember);

        MemberRole role = new MemberRole(null, MemberRoleCode.USER);
        registerMember.addRole(role);
        memberRoleJpaRepository.save(role);

        log.debug("register member: {}", registerMember);
        log.debug("roles: {}", registerMember.getRoles());

        return registerMember.getId();
    }
}
