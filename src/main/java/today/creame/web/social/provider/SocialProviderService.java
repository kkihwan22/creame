package today.creame.web.social.provider;

public interface SocialProviderService {
    String generateUrl();

    String getToken(String code);

    String getUserEmail(String token);

}
