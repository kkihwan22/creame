package today.creame.web.ranking.endpoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.ranking.application.RankingService;
import today.creame.web.ranking.application.model.RankingResult;
import today.creame.web.ranking.endpoint.rest.io.RankingResponse;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class RankingRestController {

    private final RankingService rankingService;

    @GetMapping("/public/v1/rankings")
    public ResponseEntity<Body<List<RankingResponse>>> list() {
        List<RankingResult> rankingResults = rankingService.list();
        List<RankingResponse> responses = rankingResults.stream().map(RankingResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(BodyFactory.success(responses));

    }
}
