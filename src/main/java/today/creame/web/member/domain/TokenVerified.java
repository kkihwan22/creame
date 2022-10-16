package today.creame.web.member.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

@Getter @ToString
public class TokenVerified {

    private final boolean verified;
    private UserDetails userDetails;


    public TokenVerified(boolean verified, UserDetails userDetails) {
        this.verified = verified;
        this.userDetails = userDetails;
    }

    public static TokenVerified success(UserDetails userDetails) {
        return new TokenVerified(true, userDetails);
    }

    public static TokenVerified failure(UserDetails userDetails) {
        return new TokenVerified(false, userDetails);
    }

}