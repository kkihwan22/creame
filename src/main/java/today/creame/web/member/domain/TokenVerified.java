package today.creame.web.member.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Getter @ToString
public class TokenVerified {

    private final boolean verified;
    private final String username;
    private Collection<GrantedAuthority> authorities;

    public TokenVerified(boolean verified, String username) {
        this.verified = verified;
        this.username = username;
    }

    private void setAuthorities(Collection<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public static TokenVerified success(String username, String strAuthorities) {
        TokenVerified tokenVerified = new TokenVerified(true, username);
        tokenVerified.setAuthorities(
                Arrays.stream(strAuthorities.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
        return tokenVerified;
    }

    public static TokenVerified failure(String username, String strAuthorities) {
        TokenVerified tokenVerified = new TokenVerified(false, username);
        tokenVerified.setAuthorities(
                Arrays.stream(strAuthorities.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList()));
        return tokenVerified;
    }

}