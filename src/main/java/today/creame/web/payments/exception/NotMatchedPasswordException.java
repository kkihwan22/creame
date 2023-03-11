package today.creame.web.payments.exception;

import static today.creame.web.payments.exception.PaymentErrorCode.NOT_MATCHED_PASSWORD;

import today.creame.web.share.exception.BusinessException;

public class NotMatchedPasswordException extends BusinessException {

    private final static PaymentErrorCode errorCode = NOT_MATCHED_PASSWORD;

    public NotMatchedPasswordException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
