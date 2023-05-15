package today.creame.web.config.security;

import java.util.Set;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter @ToString
public class CustomUserDetails implements UserDetails {

    private Long id;
    private String nickname;

    private String username;
    private String password;
    private boolean isEnabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private Set<SimpleGrantedAuthority> authorities;

    public CustomUserDetails(Long id, String nickname, String username, String password, Set<SimpleGrantedAuthority> authorities) {
        this.id = id;
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.authorities = authorities;

        this.isEnabled = true;
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
    }

    public CustomUserDetails(Long id) {
        this.id = id;
    }
}
