package today.creame.web.influence.entrypoint.rest;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.HotInfluenceService;
import today.creame.web.influence.entrypoint.rest.io.HotInfluenceUpdateRequest;
import today.creame.web.share.entrypoint.BaseRestController;

@RequiredArgsConstructor
@RestController
public class HotInfluenceRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceRestController.class);
    private final HotInfluenceService hotInfluenceService;

    @PutMapping("/admin/v1/hot-influences/{id}")
    public void update(
        @PathVariable Long id,
        @RequestBody @Valid HotInfluenceUpdateRequest request, BindingResult bindingResult
    ) {

    }
}
