package today.creame.web.payments.exception;

import static today.creame.web.payments.exception.PaymentErrorCode.ISSUE_BILL_KEY;

import today.creame.web.share.exception.BusinessException;

public class PaymentFailureException extends BusinessException {

    private final static PaymentErrorCode errorCode = ISSUE_BILL_KEY;

    public PaymentFailureException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}