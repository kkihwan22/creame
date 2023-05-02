package today.creame.web.social.application;

import today.creame.web.social.domain.ProviderType;

import java.util.Map;

public interface SocialService {
    String initSocialUrl(ProviderType type);

    Map<String, String> login(ProviderType type, String code);

}
