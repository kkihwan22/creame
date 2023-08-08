package today.creame.web.ranking.application;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import today.creame.web.ranking.domain.ConsultationProduct;

import static today.creame.web.ranking.domain.QConsultationProduct.consultationProduct;

@Service
@RequiredArgsConstructor
public class ConsultationProductQueryImpl implements ConsultationProductQuery {

    private final JPAQueryFactory query;

    @Override
    public Page<ConsultationProduct> list(Pageable pageable) {
        QueryResults<ConsultationProduct> result = query
                .selectFrom(consultationProduct)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(consultationProduct.ranking.level.asc(), consultationProduct.budgetAmount.asc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
