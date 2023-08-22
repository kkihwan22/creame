package today.creame.web.notice.application;

import today.creame.web.notice.application.model.NoticeRegisterParameter;
import today.creame.web.notice.application.model.NoticeResult;

public interface NoticeService {

    NoticeResult getDetail(Long id);
    Long register(NoticeRegisterParameter parameter);
    void update(NoticeRegisterParameter parameter);
    void delete(Long id);
}
