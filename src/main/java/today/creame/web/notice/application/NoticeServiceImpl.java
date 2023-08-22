package today.creame.web.notice.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.notice.application.model.NoticeRegisterParameter;
import today.creame.web.notice.application.model.NoticeResult;
import today.creame.web.notice.domain.Notice;
import today.creame.web.notice.domain.NoticeJpaRepository;
import today.creame.web.notice.exception.NotFoundNoticeException;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeJpaRepository noticeJpaRepository;

    @Override
    public NoticeResult getDetail(Long id) {
        Notice notice = noticeJpaRepository.findByIdAndDeletedIsFalse(id).orElseThrow(NotFoundNoticeException::new);
        return new NoticeResult(notice);
    }

    @Transactional
    @Override
    public Long register(NoticeRegisterParameter parameter) {
        Notice notice = parameter.toEntity();
        noticeJpaRepository.save(notice);
        return notice.getId();
    }

    @Transactional
    @Override
    public void update(NoticeRegisterParameter parameter) {
        Notice notice = noticeJpaRepository.findByIdAndDeletedIsFalse(parameter.getId()).orElseThrow(NotFoundNoticeException::new);
        notice.changeNotice(parameter.getTitle(), parameter.getContent());
        noticeJpaRepository.save(notice);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Notice notice = noticeJpaRepository.findByIdAndDeletedIsFalse(id).orElseThrow(NotFoundNoticeException::new);
        notice.delete();
        noticeJpaRepository.save(notice);
    }
}
