package today.creame.web.matching.applicaton;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.matching.applicaton.model.ReviewKindStatResult;
import today.creame.web.matching.applicaton.model.ReviewResult;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.ReviewKindsStat;
import today.creame.web.matching.domain.ReviewKindsStatJpaRepository;
import today.creame.web.share.support.SortSupport;

@RequiredArgsConstructor
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final Logger log = LoggerFactory.getLogger(ReviewQueryServiceImpl.class);
    private final MatchingJapRepository matchingJapRepository;
    private final ReviewKindsStatJpaRepository reviewKindsStatJpaRepository;

    @Override
    public List<ReviewResult> listReviewByInfluence(Long influenceId, String sort) {
        List<Matching> results = matchingJapRepository.findMatchingsByInfluenceId(influenceId, SortSupport.convert(sort));
        log.debug("results:{}", results);
        return results.stream().map(result -> new ReviewResult(result)).collect(Collectors.toList());
    }

    @Override
    public List<ReviewKindStatResult> getReviewKindsStatByInfluence(Long influenceId) {
        List<ReviewKindsStat> results = reviewKindsStatJpaRepository.findReviewKindsStatByInfluenceId(influenceId);
        log.debug("results: {}", results);
        return results.stream()
            .map(result -> new ReviewKindStatResult(result.getKinds(), result.getTotal()))
            .collect(Collectors.toList());
    }
}
