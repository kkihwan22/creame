package today.creame.web.share.aspect.permit.exception;

import lombok.Getter;

public enum PermitErrorCode {

    NO_PERMIT(200, "권한이 없습니다."),

    ;

    @Getter
    private final int code;

    @Getter
    private final String message;

    PermitErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
