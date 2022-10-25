package today.creame.web.share.exception;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.exception.model.ErrorBodyData;


@Getter @ToString
public class BusinessException extends RuntimeException {
    private final int code;
    private final String message;
    private final List<ErrorBodyData> errors;

    public BusinessException(int code, String message, List<ErrorBodyData> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public BusinessException(int code, String message) {
        this(code, message, Collections.emptyList());
    }
}
