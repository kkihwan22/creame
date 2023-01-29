package today.creame.web.matching.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.share.entrypoint.BaseRestController;

@RequiredArgsConstructor
@RestController
public class MatchingRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MatchingRestController.class);
}
