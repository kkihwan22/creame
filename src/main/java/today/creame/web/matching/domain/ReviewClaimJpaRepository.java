package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewClaimJpaRepository extends JpaRepository<ReviewClaim, Long> {

    Optional<ReviewClaim> findReviewClaimByMatchingReview_IdAAndReporter(Long reviewId, Long reporter);
}