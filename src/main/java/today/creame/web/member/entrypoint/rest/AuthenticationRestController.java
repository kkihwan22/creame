package today.creame.web.member.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import today.creame.web.member.application.AuthenticationService;
import today.creame.web.share.entrypoint.*;

@RequiredArgsConstructor
@RestController
public class AuthenticationRestController {
    private final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);
    private final AuthenticationService authenticationService;

    @PostMapping("/public/v1/login")
    public void login() {
        log.debug("Do nothing.");
    }

    @PostMapping("/public/v1/token/refresh")
    public ResponseEntity<Body<SimpleBodyData<String>>> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        log.debug("[ Authorization Header ] token : {} ", token);
        String newAccessToken = authenticationService.issueAccessTokenByRefreshToken(token);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData(newAccessToken)));
    }
}