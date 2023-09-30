package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import today.creame.web.influence.application.model.RankConditionResult;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.model.MatchingStatisticsParameter;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InfluenceRankServiceImpl implements InfluenceRankService {

    private final InfluenceJpaRepository influenceJpaRepository;
    private final MatchingQueryService matchingQueryService;

    @Override
    public RankConditionResult getRankCondition() {
        Long id = SecurityContextSupporter.getId();
        Influence influence = influenceJpaRepository.findById(id).orElseThrow(NotFoundInfluenceException::new);
        MatchingStatisticsParameter parameter = MatchingStatisticsParameter.fromLastMonthBefore(id, 3);
        List<MatchingStatisticsResult> result3M = matchingQueryService.getConsultationHoursPerMonth(parameter);
        Map<String, Long> map = new HashMap<>();
        result3M.forEach(it -> {
            map.put(it.getYearMonth(), map.getOrDefault(it.getYearMonth(), 0L) + it.getTotalCoin());
        });

        List<String> sortedKeys = map.keySet().stream().sorted().collect(Collectors.toList());
        Long nextRankAmount = influence.getRank().getNextRankAmount();
        int meetConditionCount = 0;
        for (String key : sortedKeys) {
            if (nextRankAmount > map.get(key)) {
                break;
            }
            meetConditionCount++;
        }

        return new RankConditionResult(influence, 3, meetConditionCount);
    }
}
