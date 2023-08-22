package today.creame.web.faq.application;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import today.creame.web.common.support.Utils;
import today.creame.web.faq.application.model.FaqSearchParameter;
import today.creame.web.faq.domain.Faq;

import static today.creame.web.faq.domain.QFaq.faq;

@RequiredArgsConstructor
@Service
public class FaqQueryImpl implements FaqQuery{
    private final JPAQueryFactory query;

    @Override
    public Page<Faq> list(FaqSearchParameter searchParameter, Pageable pageable) {
        QueryResults<Faq> result = query
                .selectFrom(faq)
                .where(faq.deleted.eq(false),
                        Utils.equalsOperation(faq.category, searchParameter.getCategory()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(faq.orderNumber.asc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
