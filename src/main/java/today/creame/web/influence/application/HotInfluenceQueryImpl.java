package today.creame.web.influence.application;

import static today.creame.web.influence.domain.QHotInfluence.hotInfluence;
import static today.creame.web.influence.domain.QInfluence.influence;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.HotInfluenceResult;
import today.creame.web.influence.domain.*;

@RequiredArgsConstructor
@Service
public class HotInfluenceQueryImpl implements HotInfluenceQuery {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceQueryImpl.class);
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;
    private final JPAQueryFactory query;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final QHotInfluence h = hotInfluence;
    private final QInfluence i = influence;

    @Override
    public List<HotInfluenceResult> getEnabledHotInfluenceList() {
        List<HotInfluence> hotInfluences = hotInfluenceJpaRepository.findHotInfluencesByEnabled(true);
        log.debug("hotInfluences: {}", hotInfluences);

        return hotInfluences.stream()
            .map(entity -> new HotInfluenceResult(entity))
            .collect(Collectors.toList());
    }

    @Override
    public Page<HotInfluence> list(Pageable pageable) {

    QueryResults<HotInfluence> result = query
            .selectFrom(hotInfluence)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(getOrderSpecifier(pageable))
            .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    private static final Map<String, Expression<?>> HOTINFLUENCE_PROPERTY_MAP = new HashMap<>();
    static {
        HOTINFLUENCE_PROPERTY_MAP.put("id", hotInfluence.id);
        HOTINFLUENCE_PROPERTY_MAP.put("nickname", hotInfluence.nickname);
        HOTINFLUENCE_PROPERTY_MAP.put("orderNumber", hotInfluence.orderNumber);
        HOTINFLUENCE_PROPERTY_MAP.put("createdDateTime", hotInfluence.createdDateTime);
        HOTINFLUENCE_PROPERTY_MAP.put("updatedDateTime", hotInfluence.updatedDateTime);
        }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            Sort.Order order = pageable.getSort().iterator().next();
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Expression<?> property = HOTINFLUENCE_PROPERTY_MAP.get(order.getProperty());
            if (property != null) {
                return new OrderSpecifier(direction, property);
            }
        }
        return null;
    }
}
