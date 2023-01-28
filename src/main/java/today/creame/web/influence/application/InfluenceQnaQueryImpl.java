package today.creame.web.influence.application;

import static today.creame.web.influence.domain.QInfluenceQna.influenceQna;
import static today.creame.web.member.domain.QMember.member;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaQuestionQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.influence.domain.InfluenceQnaJpaRepository;
import today.creame.web.influence.domain.QInfluenceQna;
import today.creame.web.member.domain.QMember;

@RequiredArgsConstructor
@Service
public class InfluenceQnaQueryImpl implements InfluenceQnaQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaQueryImpl.class);
    private final InfluenceQnaJpaRepository influenceQnaJpaRepository;
    private final JPAQueryFactory query;

    @Override
    public List<InfluenceQnaResult> pagingList(InfluenceQnaQueryParameter parameter) {
        log.debug("parameter: {}", parameter);

        QInfluenceQna q = influenceQna;
        QMember m = member;

        List<InfluenceQna> results = query
            .selectFrom(influenceQna)
            .join(influenceQna.questioner)
            .fetchJoin()
            .where(buildWhere(parameter))
            .offset(parameter.getPageable().getOffset())
            .limit(parameter.getPageable().getPageSize())
            .fetch();

        return results.stream().map(result -> new InfluenceQnaResult(parameter.requesterId, result)).collect(Collectors.toList());
    }

    @Override
    public List<InfluenceQnaResult> pagingListByQuestions(InfluenceQnaQuestionQueryParameter parameter) {
        return null;
    }

    @Override
    public List<InfluenceQnaResult> pagingListByAnswers(InfluenceQnaAnswerParameter parameter) {
        return null;
    }

    private BooleanBuilder buildWhere(InfluenceQnaQueryParameter parameter) {
        BooleanBuilder where = new BooleanBuilder();
        if (parameter.requesterId != null)
            where.and(influenceQna.questioner.id.eq(parameter.requesterId));

        return where;
    }
}
