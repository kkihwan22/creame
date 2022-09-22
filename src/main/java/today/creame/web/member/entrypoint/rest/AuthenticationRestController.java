package today.creame.web.member.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AuthenticationRestController {
    private final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);

    @PostMapping("/public/v1/login")
    public void login() {
        log.debug("Do nothing.");
    }

    @GetMapping("/public/v1/token/refresh")
    public void refreshToken() {

    }
}
