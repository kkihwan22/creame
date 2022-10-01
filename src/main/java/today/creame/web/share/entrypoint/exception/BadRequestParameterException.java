package today.creame.web.share.entrypoint.exception;


import today.creame.web.share.exception.BusinessException;
import today.creame.web.share.exception.model.ErrorBodyData;

import java.util.*;

public class BadRequestParameterException extends BusinessException {
    public BadRequestParameterException(List<ErrorBodyData> errors) {
        super(400, "error.badrequestparameter", errors);
    }
}
