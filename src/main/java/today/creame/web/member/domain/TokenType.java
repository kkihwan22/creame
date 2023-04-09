package today.creame.web.member.domain;

import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

public enum TokenType {

    ACCESS_TOKEN((60 * 60 * 24 * 7L)),
    REFRESH_TOKEN((60 * 60 * 24 * 7L))
    ;

    @Getter
    private final long expireTime;

    TokenType(long expireTime) {
        this.expireTime = expireTime;
    }

    public Token factory(UserDetails userDetails) {
        return Token.build(this, userDetails);
    }
}