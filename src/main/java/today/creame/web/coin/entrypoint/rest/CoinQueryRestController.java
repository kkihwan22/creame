package today.creame.web.coin.entrypoint.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.coin.application.CoinsService;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.coin.entrypoint.rest.io.MyCoinHistoryResponse;
import today.creame.web.coin.entrypoint.rest.io.MyCoinStatResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.support.SecurityContextSupporter;

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
        @RequestParam(required = false, defaultValue = "1") int month
    ) {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);

        List<MyCoinHistoryResponse> list = new ArrayList<>();

        LocalDateTime now = LocalDateTime.now();
        MyCoinHistoryResponse response1 = new MyCoinHistoryResponse(
            CoinsHistoryType.CHARGING, 10000, 10000, now);

        MyCoinHistoryResponse response2 = new MyCoinHistoryResponse(
            CoinsHistoryType.REWARD, 500, 10500, now);

        MyCoinHistoryResponse response3 = new MyCoinHistoryResponse(
            CoinsHistoryType.USING, 5000, 5500, now);

        list.add(response1);
        list.add(response2);
        list.add(response3);
        return ResponseEntity.ok(BodyFactory.success(list));
    }
}
