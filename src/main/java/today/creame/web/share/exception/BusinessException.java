package today.creame.web.share.exception;

import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.exception.model.ErrorBodyData;
import today.creame.web.share.exception.model.MetaBodyData;


@Getter @ToString
public class BusinessException extends RuntimeException {
    private final int code;
    private final String message;
    private final List<ErrorBodyData> errors;
    private final MetaBodyData metaBodyData;

    public BusinessException(int code, String message, List<ErrorBodyData> errors, MetaBodyData metaBodyData) {
        this.code = code;
        this.message = message;
        this.errors = errors;
        this.metaBodyData = metaBodyData;
    }

    public BusinessException(int code, String message) {
        this(code, message, Collections.emptyList(), null);
    }

    public BusinessException(int code, String message, List<ErrorBodyData> errors) {
        this(code, message, errors, null);
    }

    public BusinessException(int code, String message, MetaBodyData metaBodyData) {
        this(code, message, Collections.emptyList(), metaBodyData);
    }
}
