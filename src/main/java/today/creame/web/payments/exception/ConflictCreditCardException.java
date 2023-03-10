package today.creame.web.payments.exception;

import today.creame.web.share.exception.BusinessException;

public class ConflictCreditCardException extends BusinessException {

    private final static PaymentErrorCode errorCode = PaymentErrorCode.CONFLICT_CREDIT_CARD;

    public ConflictCreditCardException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
