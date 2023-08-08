package today.creame.web.ranking.exception;

import lombok.Getter;

public enum RankingErrorCode {
    NOT_FOUND_RANKING(7000, "랭킹 정보를 찾을 수 없습니다."),
    ;

    @Getter
    private final int code;
    @Getter
    private final String message;

    RankingErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
