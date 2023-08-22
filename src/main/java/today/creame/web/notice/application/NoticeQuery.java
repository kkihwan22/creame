package today.creame.web.notice.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.notice.domain.Notice;

public interface NoticeQuery {
    Page<Notice> list(Pageable pageable);
}
