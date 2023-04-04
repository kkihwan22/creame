package today.creame.web.influence.application;

import java.util.List;
import java.util.stream.Collectors;
import com.querydsl.core.QueryResults;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.common.support.Utils;
import today.creame.web.influence.application.model.InfluenceApplicationResult;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.domain.InfluenceApplicationJpaRepository;
import today.creame.web.influence.domain.InfluenceApplicationStatus;

import static today.creame.web.influence.domain.QInfluenceApplication.influenceApplication;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class InfluenceApplicationQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationQuery.class);
    private final InfluenceApplicationJpaRepository influenceApplicationJpaRepository;
    private final JPAQueryFactory query;

    public List<InfluenceApplicationResult> list(List<InfluenceApplicationStatus> influenceApplicationStatuses) {
        log.debug("in status: {}", influenceApplicationStatuses);
        List<InfluenceApplication> applications = influenceApplicationJpaRepository.findInfluenceApplicationByStatusIn(influenceApplicationStatuses);
        log.debug("applications: {}", applications);

        return applications.stream()
            .map(application -> new InfluenceApplicationResult(application))
            .collect(Collectors.toList());
    }

    public Page<InfluenceApplication> list(List<InfluenceApplicationStatus> influenceApplicationStatuses, Pageable pageable) {
        QueryResults<InfluenceApplication> result = query
                .selectFrom(influenceApplication)
                .where(Utils.inOperation(influenceApplication.status, influenceApplicationStatuses))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()){
                    case "id" :
                        return new OrderSpecifier(direction, influenceApplication.id);
                    case "email":
                        return new OrderSpecifier(direction, influenceApplication.email);
                    case "name":
                        return new OrderSpecifier(direction, influenceApplication.name);
                    case "nickname":
                        return new OrderSpecifier(direction, influenceApplication.nickname);
                    case "status":
                        return new OrderSpecifier(direction, influenceApplication.status);
                    case "createdDateTime":
                        return new OrderSpecifier(direction, influenceApplication.createdDateTime);
                    case "updatedDateTime":
                        return new OrderSpecifier(direction, influenceApplication.updatedDateTime);
                }
            }
        }
        return null;
    }
}