package today.creame.web.ranking.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import today.creame.web.ranking.application.model.RankingResult;
import today.creame.web.ranking.domain.Ranking;
import today.creame.web.ranking.domain.RankingJpaRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl implements RankingService{
    private final RankingJpaRepository rankingJpaRepository;

    @Override
    public List<RankingResult> list() {
        List<Ranking> rankings = rankingJpaRepository.findAll();
        if(CollectionUtils.isEmpty(rankings)) {
            return Collections.EMPTY_LIST;
        }

        return rankings.stream().map(RankingResult::new).collect(Collectors.toList());
    }
}
