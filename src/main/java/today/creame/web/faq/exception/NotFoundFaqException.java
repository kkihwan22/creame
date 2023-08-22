package today.creame.web.faq.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundFaqException extends BusinessException {

    private final static FaqErrorCode errorCode = FaqErrorCode.NOT_FOUND_FAQ;

    public NotFoundFaqException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
