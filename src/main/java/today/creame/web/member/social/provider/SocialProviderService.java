package today.creame.web.member.social.provider;

import today.creame.web.member.social.provider.io.ProviderProfileResult;

public interface SocialProviderService {
    String generateUrl();

    String getToken(String code);

    ProviderProfileResult getInfo(String token);

}
