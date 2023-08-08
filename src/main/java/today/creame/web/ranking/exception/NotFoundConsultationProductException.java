package today.creame.web.ranking.exception;

import today.creame.web.share.exception.BusinessException;

public class NotFoundConsultationProductException extends BusinessException {

    private final static ConsultationProductErrorCode errorCode = ConsultationProductErrorCode.NOT_FOUND_CONSULTATION_PRODUCT;

    public NotFoundConsultationProductException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
