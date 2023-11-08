package today.creame.web.m2net.exception;

import today.creame.web.share.exception.BusinessException;

import static today.creame.web.m2net.exception.M2NetErrorCode.NOT_ENOUGH_BALANCE;

public class NotEnoughBalanceException extends BusinessException {

    private final static M2NetErrorCode errorCode = NOT_ENOUGH_BALANCE
            ;

    public NotEnoughBalanceException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
