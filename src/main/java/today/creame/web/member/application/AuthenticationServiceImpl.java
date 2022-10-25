package today.creame.web.member.application;

import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.config.security.exception.TokenNotExistException;
import today.creame.web.member.application.model.RefreshTokenParameter;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberToken;
import today.creame.web.member.domain.MemberTokenJpaRepository;
import today.creame.web.member.domain.Token;
import today.creame.web.member.domain.TokenType;
import today.creame.web.member.domain.TokenVerified;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final MemberTokenJpaRepository memberTokenJpaRepository;

    @Transactional
    @Override
    public void saveRefreshToken(RefreshTokenParameter parameter) {
        log.debug("[ parameter ] : {}", parameter);
        MemberToken memberToken = parameter.toEntity();
        memberTokenJpaRepository.save(memberToken);
    }

    @Transactional(readOnly = true)
    @Override
    public String issueAccessTokenByRefreshToken(String refreshToken) {

        MemberToken findToken = memberTokenJpaRepository
                .findMemberTokenByRefreshToken(refreshToken)
                .orElseThrow(TokenNotExistException::new);

        log.debug("find token :{}", findToken);

        Token token = new Token(findToken.getRefreshToken(), TokenType.REFRESH_TOKEN);
        TokenVerified verify = token.verify();

        Member member = findToken.getMember();
        Set<SimpleGrantedAuthority> authorities = member.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getCodeName().name()))
                .collect(Collectors.toSet());

        Token accessToken = TokenType.ACCESS_TOKEN.factory(new CustomUserDetails(member.getId(), member.getNickname(), member.getEmail(), "", authorities));

        log.debug("new access token: {}", accessToken);
        return accessToken.getValue();
    }
}
