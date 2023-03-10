package today.creame.web.payments.exception;

import static today.creame.web.payments.exception.PaymentErrorCode.ILLEGAL_CREDIT_CARD_DATA;

import today.creame.web.share.exception.BusinessException;

public class IllegalCreditCardDataException extends BusinessException {

    private final static PaymentErrorCode errorCode = ILLEGAL_CREDIT_CARD_DATA;

    public IllegalCreditCardDataException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
