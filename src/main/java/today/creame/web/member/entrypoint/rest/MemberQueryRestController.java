package today.creame.web.member.entrypoint.rest;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.member.application.MemberQuery;
import today.creame.web.member.application.model.MyQuestionsQueryParameter;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

@RequiredArgsConstructor
@RestController
public class MemberQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MemberQueryRestController.class);
    private final MemberQuery memberQuery;

    @GetMapping("/api/members/{id}/questions")
    public ResponseEntity<Body<List<InfluenceQnaResult>>> pagingListMyQuestions(
        @PathVariable Long id,
        @RequestParam(required = false) int page,
        @RequestParam(required = false) int size,
        @RequestParam(required = false) boolean answered
    ) {
        List<InfluenceQnaResult> results = memberQuery.pagingListMyQuestions(
            new MyQuestionsQueryParameter(page, size, answered));
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }
}
