package today.creame.web.influence.application;

import static today.creame.web.influence.domain.QInfluenceQna.influenceQna;
import static today.creame.web.member.domain.QMember.member;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
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
    public List<InfluenceQnaResult> listByPaging(InfluenceQnaQueryParameter parameter) {
        log.debug("parameter: {}", parameter);

        QInfluenceQna q = influenceQna;
        QMember m = member;

        List<InfluenceQna> results = query
            .selectFrom(influenceQna)
            .join(influenceQna.questioner)
            .fetchJoin()
            .where(isMe(parameter.me, parameter.requesterId))
            .offset(parameter.getPageable().getOffset())
            .limit(parameter.getPageable().getPageSize())
            .fetch();

        return results.stream().map(result -> new InfluenceQnaResult(parameter.requesterId, result)).collect(Collectors.toList());
    }

    private BooleanExpression isMe(boolean me, Long requesterId) {
        if (!me) {
            return null;
        }

        return influenceQna.questioner.id.eq(requesterId);
    }
}
