package today.creame.web.matching.entrypoint.rest;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.model.MatchingStatisticsParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;
import today.creame.web.matching.entrypoint.rest.io.MatchingStatisticsDetailResponse;
import today.creame.web.matching.entrypoint.rest.io.MatchingStatisticsResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.support.SecurityContextSupporter;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static today.creame.web.matching.domain.PaidType.PRE;

@RequiredArgsConstructor
@RestController
public class SettlementRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(SettlementRestController.class);
    private final MatchingQueryService matchingQueryService;

    @GetMapping("/api/v1/matchings/stat")
    public ResponseEntity<Body<List<MatchingStatisticsResponse>>> getMatchingsStat(
            @RequestParam(name = "to") String toDate,
            @RequestParam(name = "from") String fromDate) {
        Long id = SecurityContextSupporter.getId();
        List<MatchingStatisticsResult> results = matchingQueryService.getConsultationHoursPerMonth(new MatchingStatisticsParameter(id, fromDate, toDate));

        Map<String, List<MatchingStatisticsResult>> map = results.stream().collect(Collectors.groupingBy(MatchingStatisticsResult::getYearMonth));
        Set<String> keys = map.keySet();
        List<MatchingStatisticsResponse> responses = new ArrayList<>();

        for(String key: keys) {
            List<MatchingStatisticsResult> list = map.get(key);
            Integer preTime = 0, postTime = 0;
            Long totalCoin = 0L;

            for(MatchingStatisticsResult target: list) {
                if(PRE.equals(target.getPaidType())) {
                    preTime = target.getTotalTime();
                } else {
                    postTime = target.getTotalTime();
                }
                totalCoin += target.getTotalCoin();
            }
            MatchingStatisticsResponse.MatchingStatisticsDetail detail = new MatchingStatisticsResponse.MatchingStatisticsDetail(totalCoin, postTime, preTime);
            responses.add(new MatchingStatisticsResponse(key, detail));
        }

        return ResponseEntity.ok(BodyFactory.success(responses.stream().sorted(Comparator.comparing(MatchingStatisticsResponse::getYearMonth).reversed()).collect(Collectors.toList())));
    }

    @GetMapping("/api/v1/matchings/stat/selected")
    public ResponseEntity<Body<MatchingStatisticsDetailResponse>> getMatchingsStatSelectedDate(@RequestParam String date) {
        if(Objects.isNull(date)) {
            return ResponseEntity.ok(BodyFactory.success(null));
        }
        Long id = SecurityContextSupporter.getId();
        List<MatchingStatisticsResult> results = matchingQueryService.getConsultationHoursPerMonth(new MatchingStatisticsParameter(id, date));

        Map<String, List<MatchingStatisticsResult>> map = results.stream().collect(Collectors.groupingBy(MatchingStatisticsResult::getYearMonth));
        Set<String> keys = map.keySet();
        MatchingStatisticsDetailResponse response = null;
        for(String key: keys) {
            List<MatchingStatisticsResult> list = map.get(key);
            Integer preTime = 0, postTime = 0, totalTime = 0;
            Long preCoin = 0L, postCoin = 0L, totalCoin = 0L;
            int totalSeconds = 0;

            for(MatchingStatisticsResult target: list) {
                if(PRE.equals(target.getPaidType())) {
                    preTime = target.getTotalTime();
                    preCoin = target.getTotalCoin();
                } else {
                    postTime = target.getTotalTime();
                    postCoin = target.getTotalCoin();
                }
                totalCoin += target.getTotalCoin();
                totalSeconds += target.getTotalTime();
            }

            totalTime = totalSeconds;
            response = new MatchingStatisticsDetailResponse(key, postTime, postCoin, preTime, preCoin, totalTime, totalCoin);
        }

        return ResponseEntity.ok(BodyFactory.success(response));
    }

}
