package today.creame.web.matching.applicaton;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.matching.applicaton.model.ReviewCreateParameter;
import today.creame.web.matching.applicaton.model.ReviewReplyParameter;
import today.creame.web.matching.domain.*;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.matching.exception.NotFoundReviewException;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {
    private final Logger log = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final MatchingJapRepository matchingJapRepository;
    private final MatchingReviewJapRepository matchingReviewJapRepository;
    private final ReviewKindsStatJpaRepository reviewKindsStatJpaRepository;
    private final ReviewLikedJpaRepository reviewLikedJpaRepository;
    private final ReviewClaimJpaRepository reviewClaimJpaRepository;

    @Transactional
    @Override
    public void review(ReviewCreateParameter parameter) {
        Matching matching = matchingJapRepository.findById(parameter.getMatchingId()).orElseThrow(NotFoundMatchingException::new);
        log.debug("matching: {}", matching);
        matching.addReview(parameter.getRate(), parameter.getCategory(), parameter.getReviewKinds(), parameter.getContent());
        Long id = matching.getInfluence().getId();
        reviewKindsStatJpaRepository.findReviewKindsStatByInfluenceIdAndKinds(id, parameter.getReviewKinds()).ifPresentOrElse(
                        it -> it.incrCount(),
                        () -> reviewKindsStatJpaRepository.save(new ReviewKindsStat(id, parameter.getReviewKinds())));
    }

    @Transactional
    @Override
    public void answer(ReviewReplyParameter parameter) {
        MatchingReview review = matchingReviewJapRepository.findById(parameter.getId()).orElseThrow(NotFoundReviewException::new);
        log.debug("review: {}", review);
        review.registerReply(parameter.getContent());
    }

    @Transactional
    @Override
    public void like(Long reviewId) {
        Long id = SecurityContextSupporter.getId();
        MatchingReview review = matchingReviewJapRepository.findById(reviewId).orElseThrow(NotFoundReviewException::new);
        Optional<ReviewLiked> optionalReviewLiked = reviewLikedJpaRepository.findReviewLikedByReviewIdAndMemberId(reviewId, id);

        if (optionalReviewLiked.isEmpty()) {
            ReviewLiked newLiked = reviewLikedJpaRepository.save(new ReviewLiked(reviewId, id));
            review.changeLikeCount(newLiked);
        } else {
            ReviewLiked liked = optionalReviewLiked.get();
            liked.liked();
            review.changeLikeCount(liked);
        }
    }

    @Transactional
    @Override
    public void claim(Long reviewId) {
        Long id = SecurityContextSupporter.getId();
        reviewClaimJpaRepository.findReviewClaimByMatchingReview_IdAAndReporter(reviewId, id).ifPresentOrElse(reviewClaim -> { throw new RuntimeException();}, () -> new ReviewClaim());


    }
}