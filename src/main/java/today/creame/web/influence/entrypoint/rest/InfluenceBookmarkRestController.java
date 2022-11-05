package today.creame.web.influence.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.influence.application.InfluenceBookmarkService;
import today.creame.web.influence.application.model.InfluenceBookmarkParameter;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceBookmarkRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceBookmarkRestController.class);
    private final InfluenceBookmarkService influenceBookmarkService;

    @GetMapping("/api/v1/influences/{id}/bookmark")
    public ResponseEntity<Body<SimpleBodyData<Boolean>>> isBookmarked(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails) {
            CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
            log.debug("customUserDetails: {}", customUserDetails);

        boolean result = influenceBookmarkService.isBookmarked(new InfluenceBookmarkParameter(customUserDetails.getId(), id));
        log.debug("result: {}", result);

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(result)));
    }

    @PatchMapping("/api/v1/influences/{id}/bookmark/mark")
    public ResponseEntity<Body<SimpleBodyData<String>>> markBookmark(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails) {

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        log.debug("customUserDetails: {}", customUserDetails);

        influenceBookmarkService.bookmarked(new InfluenceBookmarkParameter(customUserDetails.getId(), id));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/influences/{id}/bookmark/cancel")
    public ResponseEntity<Body<SimpleBodyData<String>>> canceledBookmark(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails) {

        CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
        log.debug("customUserDetails: {}", customUserDetails);

        influenceBookmarkService.canceledBookmark(new InfluenceBookmarkParameter(customUserDetails.getId(), id));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}
