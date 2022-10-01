package today.creame.web.member.application;

import today.creame.web.member.application.model.RefreshTokenParameter;

public interface AuthenticationService {
    void saveRefreshToken(RefreshTokenParameter parameter);
    String issueAccessTokenByRefreshToken(String refreshToken);
}