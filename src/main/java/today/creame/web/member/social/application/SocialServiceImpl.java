package today.creame.web.member.social.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
import today.creame.web.member.social.domain.ProviderType;
import today.creame.web.member.social.exception.NotFoundSocialMemberException;
import today.creame.web.member.social.exception.NotFoundSocialTokenException;
import today.creame.web.member.social.exception.NotMatchSocialTypeException;
import today.creame.web.member.social.provider.SocialProviderService;
import today.creame.web.member.social.provider.apple.AppleService;
import today.creame.web.member.social.provider.google.GoogleService;
import today.creame.web.member.social.exception.NotFoundProviderProfileException;
import today.creame.web.member.social.provider.io.ProviderProfileResult;
import today.creame.web.member.social.provider.kakao.KakaoService;
import today.creame.web.member.social.provider.naver.NaverService;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService{

    private final MemberJpaRepository memberJpaRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final GoogleService googleService;
    private final NaverService naverService;
    private final AppleService appleService;
    private final KakaoService kakaoService;

    @Override
    public String initSocialUrl(ProviderType type) {
        SocialProviderService socialProviderService = null;
        switch (type) {
            case GOOGLE:
                socialProviderService =  googleService;
                break;
            case KAKAO:
                socialProviderService =  kakaoService;
                break;
            case NAVER:
                socialProviderService = naverService;
                break;
            case APPLE:
                socialProviderService = appleService;
                break;
        }

        return socialProviderService.generateUrl();
    }

    @Override
    public Map<String, String> login(ProviderType type, String code, String state) {
        SocialProviderService socialProviderService = null;
        switch (type) {
            case GOOGLE:
                socialProviderService = googleService;
                break;
            case KAKAO:
                socialProviderService = kakaoService;
                break;
            case NAVER:
                socialProviderService = naverService;
                break;
            case APPLE:
                socialProviderService = appleService;
                break;
        }

        String jwtToken = socialProviderService.getToken(code, state);
        if(Objects.isNull(jwtToken)) {
            throw new NotFoundSocialTokenException();
        }

        ProviderProfileResult result = socialProviderService.getInfo(jwtToken);
        if(StringUtils.isEmpty(result.getEmail())) {
            throw new NotFoundProviderProfileException();
        }

        log.debug("SocialServiceImpl.login >> social type {} email {} nickname {}", type, result.getEmail(), result.getNickname());

        ProviderProfileResult finalResult = new ProviderProfileResult(result.getEmail(), result.getNickname());
        Member findMember = memberJpaRepository
                .findByUserDetails(result.getEmail())
                .orElseThrow(() -> new NotFoundSocialMemberException(finalResult));

        if(!type.toString().equals(findMember.getSignupType().toString())) {
            throw new NotMatchSocialTypeException();
        }

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
