package today.creame.web.member.entrypoint.event;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import today.creame.web.member.application.AuthenticationService;
import today.creame.web.member.application.model.RefreshTokenParameter;
import today.creame.web.member.entrypoint.event.model.TokenUpdateEvent;

@RequiredArgsConstructor
@Component
public class AuthenticationEventListener {
    private final Logger log = LoggerFactory.getLogger(AuthenticationEventListener.class);
    private final AuthenticationService authenticationService;

    @EventListener
    public void listen(TokenUpdateEvent event) {
        log.debug("event: {}", event);
        authenticationService.saveRefreshToken(new RefreshTokenParameter(event.getMemberId(), event.getRefreshToken()));
    }
}
