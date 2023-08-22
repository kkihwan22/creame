package today.creame.web.notice.application;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import today.creame.web.notice.domain.Notice;

import static today.creame.web.notice.domain.QNotice.notice;

@RequiredArgsConstructor
@Service
public class NoticeQueryImpl implements NoticeQuery {
    private final JPAQueryFactory query;

    @Override
    public Page<Notice> list(Pageable pageable) {
        QueryResults<Notice> result = query
                .selectFrom(notice)
                .where(notice.deleted.eq(false))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(notice.createdDateTime.desc())
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }
}
