package today.creame.web.matching.applicaton;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.matching.applicaton.model.ReviewCreateParameter;
import today.creame.web.matching.applicaton.model.ReviewReplyParameter;
import today.creame.web.matching.domain.Matching;
import today.creame.web.matching.domain.MatchingJapRepository;
import today.creame.web.matching.domain.MatchingReview;
import today.creame.web.matching.domain.MatchingReviewJapRepository;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.matching.exception.NotFoundReviewException;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final MatchingJapRepository matchingJapRepository;
    private final MatchingReviewJapRepository matchingReviewJapRepository;

    @Transactional
    @Override
    public void review(ReviewCreateParameter parameter) {
        Matching matching = matchingJapRepository.findById(parameter.getMatchingId()).orElseThrow(NotFoundMatchingException::new);
        log.debug("matching: {}", matching);
        matching.addReview(parameter.getRate(), parameter.getCategory(), parameter.getReviewKinds(), parameter.getContent());
    }

    @Transactional
    @Override
    public void answer(ReviewReplyParameter parameter) {
        MatchingReview review = matchingReviewJapRepository.findById(parameter.getId()).orElseThrow(NotFoundReviewException::new);
        log.debug("review: {}", review);
        review.registerReply(parameter.getContent());
    }
}