package today.creame.web.matching.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.MatchingService;
import today.creame.web.matching.applicaton.ReviewQueryService;
import today.creame.web.matching.applicaton.model.*;
import today.creame.web.matching.entrypoint.rest.io.MatchingStatisticsDetailResponse;
import today.creame.web.matching.entrypoint.rest.io.MatchingStatisticsResponse;
import today.creame.web.matching.entrypoint.rest.io.MyReviewsResponse;
import today.creame.web.matching.entrypoint.rest.io.ReviewReplyResponse;
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
public class MatchingQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MatchingQueryRestController.class);
    private final MatchingQueryService matchingQueryService;
    private final ReviewQueryService reviewQueryService;
    private final MatchingService matchingService;

    @GetMapping("/api/v1/me/matching-history")
    public ResponseEntity<Body<List<MatchingHistoryResult>>> getMyMatchingHistory(@RequestParam(name = "since") int since) {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);

        List<MatchingHistoryResult> results = matchingQueryService.listMatching(id, since);
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @GetMapping("/api/v1/me/matching-history/{id}")
    public ResponseEntity<Body<MatchingResult>> getMyMatchingDetail(@PathVariable Long id) {
        log.debug("matching id: {}", id);
        MatchingResult result = matchingQueryService.getMatching(id);
        log.debug("result: {}", result);
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @GetMapping("/api/v1/me/reviews")
    public ResponseEntity<Body<MyReviewsResponse>> getMyReviews() {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        List<MyReviewResult> results = matchingQueryService.listMyReview(id);
        Map<Boolean, List<MyReviewResult>> partition = results.stream().collect(Collectors.partitioningBy(it -> it.isAnswered()));
        log.debug("partition: {}", partition);
        return ResponseEntity.ok(BodyFactory.success(new MyReviewsResponse(partition)));
    }
    @GetMapping("/api/v1/me/influence/reviews")
    public ResponseEntity<Body<ReviewReplyResponse>> getReviewsOfInfluence() {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        List<ReviewResult> results = reviewQueryService.getReviewsByInfluence(id);
        Map<Boolean, List<ReviewResult>> partition = results.stream().collect(Collectors.partitioningBy(it -> it.isAnswered()));
        log.debug("partition: {}", partition);
        return ResponseEntity.ok(BodyFactory.success(new ReviewReplyResponse(partition)));
    }

    @GetMapping("/api/v1/me/influence/matching-statistics")
    public ResponseEntity<Body<List<MatchingStatisticsResponse>>> getMatchingStatistics(@RequestParam String toDate, @RequestParam String fromDate) {
        Long id = SecurityContextSupporter.getId();
        List<MatchingStatisticsResult> results = matchingService.getConsultationHoursPerMonth(new MatchingStatisticsParameter(id, toDate, fromDate));

        Map<String, List<MatchingStatisticsResult>> map = results.stream().collect(Collectors.groupingBy(MatchingStatisticsResult::getYearMonth));
        Set<String> keys = map.keySet();
        List<MatchingStatisticsResponse> responses = new ArrayList<>();

        for(String key: keys) {
            List<MatchingStatisticsResult> list = map.get(key);
            LocalTime preTime = null, postTime = null;
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

    @GetMapping("/api/v1/me/influence/matching-current-statistics")
    public ResponseEntity<Body<MatchingStatisticsDetailResponse>> getMatchingStatisticsByCurrentMonthly(String targetDate) {
        if(Objects.isNull(targetDate)) {
            return ResponseEntity.ok(BodyFactory.success(null));
        }
        Long id = SecurityContextSupporter.getId();
        List<MatchingStatisticsResult> results = matchingService.getConsultationHoursPerMonth(new MatchingStatisticsParameter(id, targetDate, targetDate));

        Map<String, List<MatchingStatisticsResult>> map = results.stream().collect(Collectors.groupingBy(MatchingStatisticsResult::getYearMonth));
        Set<String> keys = map.keySet();
        MatchingStatisticsDetailResponse response = null;
        for(String key: keys) {
            List<MatchingStatisticsResult> list = map.get(key);
            LocalTime preTime = LocalTime.MIN, postTime = LocalTime.MIN, totalTime = LocalTime.MIN;
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
                totalSeconds += target.getTotalTime().toSecondOfDay();
            }

            totalTime = LocalTime.ofSecondOfDay(totalSeconds);
            response = new MatchingStatisticsDetailResponse(key, postTime, postCoin, preTime, preCoin, totalTime, totalCoin);
        }

        return ResponseEntity.ok(BodyFactory.success(response));
    }
}
