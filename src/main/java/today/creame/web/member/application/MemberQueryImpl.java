package today.creame.web.member.application;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.member.application.model.MeResult;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.application.model.MemberSearchParameter;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.domain.MemberNotificationPreference;
import today.creame.web.member.exception.NotFoundMemberException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static today.creame.web.member.domain.QMember.member;

@RequiredArgsConstructor
@Component
public class MemberQueryImpl implements MemberQuery {
    private final Logger log = LoggerFactory.getLogger(MemberQueryImpl.class);
    private final MemberJpaRepository memberJpaRepository;
    private final InfluenceQuery influenceQuery;
    private final JPAQueryFactory query;

    private static final Map<String, Expression<?>> MEMBER_PROPERTY_MAP = new HashMap<>();
    static {
        MEMBER_PROPERTY_MAP.put("id", member.id);
        MEMBER_PROPERTY_MAP.put("email", member.email);
        MEMBER_PROPERTY_MAP.put("createdDateTime", member.createdDateTime);
        MEMBER_PROPERTY_MAP.put("updatedDateTime", member.updatedDateTime);
    }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            Sort.Order order = pageable.getSort().iterator().next();
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Expression<?> property = MEMBER_PROPERTY_MAP.get(order.getProperty());
            if (property != null) {
                return new OrderSpecifier(direction, property);
            }
        }
        return null;
    }

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
    public MemberNotificationPreference getNotificationSetting(Long id) {
        // TODO: result 로 변경
        return memberJpaRepository
            .findById(id)
            .orElseThrow(NotFoundMemberException::new)
            .getNotificationPreferences()
            .get(0);
    }

    @Override
    public Page<Member> getList(Pageable pageable) {
        QueryResults<Member> result = query
                .selectFrom(member)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    @Override
    public String getDuplicates(Long influenceApplicationId, MemberSearchParameter parameter) {
        List<Member> members = query.selectFrom(member)
                .where(member.email.eq(parameter.getEmail())
                        .or(member.phoneNumber.eq(parameter.getPhoneNumber()))
                        .or(member.nickname.eq(parameter.getNickname())))
                .fetch();

        StringBuilder duplicates = new StringBuilder();
        for(Member duplicateMember : members) {

            if(duplicateMember.getEmail().equals(parameter.getEmail())) {
                duplicates.append("Email");
                duplicates.append(" ");
            }

            if(duplicateMember.getPhoneNumber().equals(parameter.getPhoneNumber())) {
                duplicates.append("PhoneNumber");
                duplicates.append(" ");
            }
            if(duplicateMember.getNickname().equals(parameter.getNickname())) {
                duplicates.append("Nickname");
                duplicates.append(" ");
            }

        }

        return duplicates.toString();

    }
}
