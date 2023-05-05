package today.creame.web.coin.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.coin.application.CoinsService;
import today.creame.web.coin.entrypoint.rest.io.MyCoinHistoryResponse;
import today.creame.web.coin.entrypoint.rest.io.MyCoinStatResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class CoinQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(CoinQueryRestController.class);
    private final CoinsService coinsService;

    @GetMapping("/api/v1/my-coins")
    public ResponseEntity<Body<MyCoinStatResponse>> myCoinStat() {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        return ResponseEntity.ok(BodyFactory.success(new MyCoinStatResponse(coinsService.getCoinStatByMember(id))));
    }

    @GetMapping("/api/v1/my-coins/history")
    public ResponseEntity<Body<List<MyCoinHistoryResponse>>> getMyCoinUsedHistory(
        @RequestParam(name = "since", required = false, defaultValue = "1") int since
    ) {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        List<MyCoinHistoryResponse> responses = coinsService.history(id, since).stream().map(it -> new MyCoinHistoryResponse(it)).collect(Collectors.toList());
        return ResponseEntity.ok(BodyFactory.success(responses));
    }
}
