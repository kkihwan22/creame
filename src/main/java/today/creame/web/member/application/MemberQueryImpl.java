package today.creame.web.member.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.application.model.MyQuestionsQueryParameter;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.domain.NotificationSetting;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@Component
public class MemberQueryImpl implements MemberQuery {
    private final Logger log = LoggerFactory.getLogger(MemberQueryImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final InfluenceQuery influenceQuery;

    @Override
    public MeResult getMe(Long id) {
        Member findMember = memberJpaRepository.findById(id)
            .orElseThrow(NotFoundMemberException::new);
        log.debug("find member: {}", findMember);
        return new MeResult(findMember);
    }

    @Override
    public MemberResult getId(Long id) {
        Member findMember = memberJpaRepository.findById(id).orElseThrow(NotFoundMemberException::new);
        return new MemberResult(findMember);
    }

    @Override
    public List<InfluenceQnaResult> pagingListMyQuestions(MyQuestionsQueryParameter parameter) {
        List<InfluenceQnaResult> results = influenceQuery.pagingQnas(
            new InfluenceQnaQueryParameter(parameter.getPageable(), SecurityContextSupporter.getId(), null, parameter.isAnswered()));
        log.debug("results: {}", results);

        return null;
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

    @Override
    public NotificationSetting getNotificationSetting(Long id) {
        return memberJpaRepository
            .findById(id)
            .orElseThrow(NotFoundMemberException::new)
            .getNotificationSetting();
    }
}
