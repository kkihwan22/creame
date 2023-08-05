package today.creame.web.member.application;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.InfluenceService;
import today.creame.web.influence.application.model.InfluenceUpdateParameter;
import today.creame.web.m2net.application.M2netUserService;
import today.creame.web.member.application.model.*;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.domain.MemberNotificationJpaRepository;
import today.creame.web.member.domain.MemberNotificationPreference;
import today.creame.web.member.domain.MemberRole;
import today.creame.web.member.domain.MemberRoleCode;
import today.creame.web.member.domain.MemberRoleJpaRepository;
import today.creame.web.member.exception.ConflictMemberEmailException;
import today.creame.web.member.exception.ConflictMemberNicknameException;
import today.creame.web.member.exception.ConflictMemberPhoneNumberException;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.share.aspect.permit.Permit;
import today.creame.web.share.event.SmsSendEvent;
import today.creame.web.share.support.MaskingSupporter;
import today.creame.web.share.support.RandomString;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
    private final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final MemberRoleJpaRepository memberRoleJpaRepository;
    private final MemberNotificationJpaRepository memberNotificationJpaRepository;
    private final M2netUserService m2netUserService;
    private final ApplicationEventPublisher publisher;
    private final InfluenceService influenceService;

    @Transactional
    @Permit
    @Override
    public MemberResult registerMember(MemberRegisterParameter parameter) {
        this.validation(parameter);
        Member member = parameter.toEntity();
        return new MemberResult(this.register(member, List.of(new MemberRole(null, MemberRoleCode.USER))));
    }

    @Transactional
    @Override
    public MemberResult registerMemberInfluence(MemberRegisterParameter parameter) {
        this.validation(parameter);
        return new MemberResult(this.register(parameter.toEntity(), List.of(new MemberRole(null, MemberRoleCode.USER), new MemberRole(null, MemberRoleCode.INFLUENCE))));
    }

    @Permit
    @Override
    public String forgetEmail(ForgetEmailParameter parameter) {
        Member member = memberJpaRepository
            .findByPhoneNumber(parameter.getPhoneNumber())
            .orElseThrow(NotFoundMemberException::new);

        String maskedEmail = MaskingSupporter.email(member.getEmail());
        log.debug("masked email: {}", maskedEmail);
        return maskedEmail;
    }

    @Transactional
    @Permit
    @Override
    public void forgetPassword(ForgetPasswordParameter parameter) {
        Member member = memberJpaRepository
            .findByEmailAndPhoneNumber(parameter.getEmail(), parameter.getPhoneNumber())
            .orElseThrow(NotFoundMemberException::new);

        String password = RandomString.password().nextString();
        member.changedPassword(password);
        publisher.publishEvent(new SmsSendEvent(member.getPhoneNumber(), password));
    }

    @Transactional
    @Permit
    @Override
    public void updateNickname(MemberUpdateParameter parameter) {
        Member member = memberJpaRepository
            .findById(parameter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("find member: {}", member);
        member.changedNickname(parameter.getNickname());
    }

    @Transactional
    @Permit
    @Override
    public void updatePassword(MemberUpdateParameter parameter) {
        Member member = memberJpaRepository
            .findById(parameter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("find member: {}", member);
        member.changedPassword(parameter.getPassword());
    }

    @Transactional
    @Permit
    @Override
    public void expire(MemberExpireParameter parameter) {
        // todo [ 탈퇴와 관련 확인 ]
    }

    @Transactional
    @Permit
    @Override
    public void changedNotificationCondition(NotificationSettingParameter parameter) {
        Member member = memberJpaRepository
            .findById(parameter.getId())
            .orElseThrow(NotFoundMemberException::new);
        log.debug("find member: {}", member);

        // TODO: 확인 필요
        MemberNotificationPreference notification = member.getNotificationPreferences().get(0);
        notification.changeNotificationCondition(parameter.getItem(), parameter.isCondition());
    }

    @Transactional
    @Override
    public void changedPhoneNumber(Long token, String phoneNumber) {

    }

    private void validation(MemberRegisterParameter parameter) {
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
    }

    private Member register(Member registerMember, List<MemberRole> roles) {
        registerMember.registerM2netMember(m2netUserService);
        memberJpaRepository.save(registerMember);
        memberNotificationJpaRepository.save(new MemberNotificationPreference(registerMember));
        roles.forEach(it -> {
            it.setMember(registerMember);
            memberRoleJpaRepository.save(it);
        });
        return registerMember;
    }

    public List<MemberSearchResult> findAllByEmailOrPhoneNumberOrNickname(MemberSearchParameter parameter) {
        return memberJpaRepository.findAllByEmailOrPhoneNumberOrNickname(parameter.getEmail(), parameter.getPhoneNumber(), parameter.getNickname()).stream().map(MemberSearchResult::new).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void memberRoleUpdate(Long memberId) {
        Member member = memberJpaRepository
                .findById(memberId)
                .orElseThrow(NotFoundMemberException::new);

        MemberRole role = new MemberRole(null, MemberRoleCode.INFLUENCE);
        role.setMember(member);
        memberRoleJpaRepository.save(role);
    }

    @Override
    public MemberDetailResult getDetail(Long memberId) {
        Member findMember = memberJpaRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);

        return new MemberDetailResult(findMember);
    }

    @Transactional
    @Override
    public void changeMemberInfo(MemberInfoUpdateParameter parameter) {
        if (memberJpaRepository.countMemberByNickname(parameter.getNickname()) > 0) {
            log.info(" 요청하신 닉네임({})은 이미 사용 중입니다.", parameter.getNickname());
            throw new ConflictMemberNicknameException();
        }

        if (memberJpaRepository.countMemberByPhoneNumber(parameter.getPhoneNumber()) > 0) {
            log.info(" 요청하신 휴대전화 번호({})는 이미 사용 중입니다.", parameter.getPhoneNumber());
            throw new ConflictMemberPhoneNumberException();
        }

        Member findMember = memberJpaRepository.findById(parameter.getMemberId()).orElseThrow(NotFoundMemberException::new);
        if(Objects.nonNull(parameter.getPhoneNumber())) {
            findMember.changedPhoneNumber(parameter.getPhoneNumber());
        }
        if(Objects.nonNull(parameter.getNickname())) {
            findMember.changedNickname(parameter.getNickname());
        }

        memberJpaRepository.save(findMember);

        influenceService.changeInfluenceInfo(new InfluenceUpdateParameter(findMember.getId(), findMember.getNickname(), findMember.getPhoneNumber()));

    }

    @Transactional
    @Override
    public void changePasswordByAdmin(Long memberId) {
        String password = RandomString.password().nextString();
        Member findMember = memberJpaRepository.findById(memberId).orElseThrow(NotFoundMemberException::new);
        findMember.changedPassword(password);

        publisher.publishEvent(new SmsSendEvent(findMember.getPhoneNumber(), password));
    }
}
