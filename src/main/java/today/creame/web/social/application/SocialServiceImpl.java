package today.creame.web.social.application;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.member.domain.Token;
import today.creame.web.member.domain.TokenType;
import today.creame.web.member.entrypoint.event.model.TokenUpdateEvent;
import today.creame.web.member.exception.NotFoundMemberException;
import today.creame.web.social.domain.ProviderType;
import today.creame.web.social.exception.NotFoundSocialUserInfoException;
import today.creame.web.social.provider.google.GoogleService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService{

    private final MemberJpaRepository memberJpaRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final GoogleService googleService;

    @Override
    public String initSocialUrl(ProviderType type) {
        String socialUrl = StringUtils.EMPTY;
        switch (type) {
            case GOOGLE:
                socialUrl =  googleService.generateUrl();
            case KAKAO:
            case NAVER:
            case FACEBOOk:
            case APPLE:
        }

        return socialUrl;
    }

    @Override
    public Map<String, String> login(ProviderType type, String code) {
        String userEmail = StringUtils.EMPTY;
        switch (type) {
            case GOOGLE:
                String jwtToken = googleService.getToken(code);
                userEmail = googleService.getUserEmail(jwtToken);
                break;
            case KAKAO:
            case NAVER:
            case FACEBOOk:
            case APPLE:
        }

        if(StringUtils.isEmpty(userEmail)) {
            throw new NotFoundSocialUserInfoException();
        }

        Member findMember = memberJpaRepository
                .findByUserDetails(userEmail)
                .orElseThrow(NotFoundMemberException::new);

        Set<SimpleGrantedAuthority> authorities = findMember.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role.getCodeName().name())))
                .collect(Collectors.toSet());

        CustomUserDetails user =  new CustomUserDetails(findMember.getId(), findMember.getNickname(), findMember.getEmail(), "", authorities);

        Token refresh = TokenType.REFRESH_TOKEN.factory(user);
        Token access = TokenType.ACCESS_TOKEN.factory(user);

        Map<String, String> token = new HashMap<>();
        token.put("refresh_token", refresh.getValue());
        token.put("access_token", access.getValue());

        eventPublisher.publishEvent(new TokenUpdateEvent(user.getId(), refresh.getValue()));

        return token;
    }
}
