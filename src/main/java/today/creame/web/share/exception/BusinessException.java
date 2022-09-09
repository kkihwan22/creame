package today.creame.web.share.exception;

import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;


@Getter @ToString
public class BusinessException extends RuntimeException {
    private final int code;
    private final String message;
    private final List<Error> errors;

    public BusinessException(int code, String message, List<Error> errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public BusinessException(int code, String message) {
        this(code, message, Collections.emptyList());
    }

    // TODO: 성능에 이슈여부 다시 확인 후 재정의 코드 구현하기.
    /*
    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace();
    }
    */
}
