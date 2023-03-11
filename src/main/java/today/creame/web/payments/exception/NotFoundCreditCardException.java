package today.creame.web.payments.exception;

import static today.creame.web.payments.exception.PaymentErrorCode.NOT_FOUND_CREDIT_CARD;

import today.creame.web.share.exception.BusinessException;

public class NotFoundCreditCardException extends BusinessException {

    private final static PaymentErrorCode errorCode = NOT_FOUND_CREDIT_CARD;

    public NotFoundCreditCardException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
