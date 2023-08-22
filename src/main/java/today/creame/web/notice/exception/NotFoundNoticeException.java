package today.creame.web.notice.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundNoticeException extends BusinessException {

    private final static NoticeErrorCode errorCode = NoticeErrorCode.NOT_FOUND_NOTICE;

    public NotFoundNoticeException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
