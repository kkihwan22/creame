package today.creame.web.matching.applicaton;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import today.creame.web.matching.applicaton.model.MatchingResult;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;

@RequiredArgsConstructor
@Service
public class MatchingQueryServiceImpl implements MatchingQueryService {
    private final Logger log = LoggerFactory.getLogger(MatchingQueryServiceImpl.class);
    private final MatchingJapRepository matchingJapRepository;

    @Override
    public List<MatchingResult> recentlyMyMatching(Long memberId, boolean latest) {
        List<Matching> matchings = matchingJapRepository.findMatchingsByMemberId(memberId, Sort.by(Order.desc("createdDateTime")));
        return matchings.stream().map(result -> new MatchingResult(result)).collect(Collectors.toList());
    }
}
