package today.creame.web.member.social.application;

import today.creame.web.member.social.domain.ProviderType;

import java.util.Map;

public interface SocialService {
    String initSocialUrl(ProviderType type);

    Map<String, String> login(ProviderType type, String code, String state);

}
