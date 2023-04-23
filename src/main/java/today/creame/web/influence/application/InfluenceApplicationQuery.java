package today.creame.web.influence.application;

import java.util.*;
import java.util.stream.Collectors;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.common.domain.FileResource;
import today.creame.web.common.domain.FileResourceJpaRepository;
import today.creame.web.common.support.Utils;
import today.creame.web.influence.application.model.InfluenceApplicationDetailResult;
import today.creame.web.influence.application.model.InfluenceApplicationSearchParameter;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.domain.InfluenceApplicationJpaRepository;
import today.creame.web.influence.exception.NotFoundApplicationException;

import static today.creame.web.influence.domain.QInfluenceApplication.influenceApplication;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class InfluenceApplicationQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationQuery.class);
    private final InfluenceApplicationJpaRepository influenceApplicationJpaRepository;
    private final FileResourceJpaRepository fileResourceJpaRepository;
    private final JPAQueryFactory query;

    private static final Map<String, Expression<?>> INFLUENCEAPPLICATION_PROPERTY_MAP = new HashMap<>();
    static {
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("id", influenceApplication.id);
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("email", influenceApplication.email);
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("name", influenceApplication.name);
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("nickname", influenceApplication.nickname);
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("status", influenceApplication.status);
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("createdDateTime", influenceApplication.createdDateTime);
        INFLUENCEAPPLICATION_PROPERTY_MAP.put("updatedDateTime", influenceApplication.updatedDateTime);
    }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            Sort.Order order = pageable.getSort().iterator().next();
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Expression<?> property = INFLUENCEAPPLICATION_PROPERTY_MAP.get(order.getProperty());
            if (property != null) {
                return new OrderSpecifier(direction, property);
            }
        }
        return null;
    }

    public Page<InfluenceApplication> list(InfluenceApplicationSearchParameter searchParameter, Pageable pageable) {
        QueryResults<InfluenceApplication> result = query
                .selectFrom(influenceApplication)
                .where(Utils.inOperation(influenceApplication.status, searchParameter.getStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    public InfluenceApplicationDetailResult getDetail(Long id) {
        InfluenceApplication application = influenceApplicationJpaRepository.findById(id)
                .orElseThrow(NotFoundApplicationException::new);

        List<FileResource> fileResources = Collections.EMPTY_LIST;
        if(Objects.nonNull(application.getProfileImages())) {
            List<Long> fileIds = Arrays.stream(application.getProfileImages().split(",")).map(Long::valueOf).collect(Collectors.toList());
            fileResources = fileResourceJpaRepository.findFileResourceByIdIn(fileIds);
        }
        return new InfluenceApplicationDetailResult(application, fileResources);
    }
}