package today.creame.web.member.domain;

import lombok.Getter;

public enum TokenType {

    ACCESS_TOKEN(1200L),
    REFRESH_TOKEN((60 * 60 * 24 * 7L))
    ;

    @Getter
    private final long expireTime;

    TokenType(long expireTime) {
        this.expireTime = expireTime;
    }
}
