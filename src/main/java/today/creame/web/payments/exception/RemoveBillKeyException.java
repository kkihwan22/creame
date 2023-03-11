package today.creame.web.payments.exception;

import static today.creame.web.payments.exception.PaymentErrorCode.ISSUE_BILL_KEY;

import today.creame.web.share.exception.BusinessException;

public class RemoveBillKeyException extends BusinessException {

    private final static PaymentErrorCode errorCode = ISSUE_BILL_KEY;

    public RemoveBillKeyException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
