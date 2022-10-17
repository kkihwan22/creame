package today.creame.web.common.exception;

import lombok.Getter;

public enum FileResourceErrorCode {

    NOT_EXIST_FILE_RESOURCE (9100, "업로드 파일이 존재하지 않습니다."),
    UPLOAD_FAILURE(9101, "파일 업로드에 실패하였습니다."),
    ILLEGAL_FILENAME(9102, "파일이름이 형식에 맞지 않습니다."),
    EXCEED_FILE_COUNT(9103, "업로드 파일의 수가 초과되었습니다."),
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    FileResourceErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
