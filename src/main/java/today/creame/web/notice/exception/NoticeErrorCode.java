package today.creame.web.notice.exception;

import lombok.Getter;

public enum NoticeErrorCode {
    NOT_FOUND_NOTICE(9000, "공지사항 정보를 찾을 수 없습니다."),
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    NoticeErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
