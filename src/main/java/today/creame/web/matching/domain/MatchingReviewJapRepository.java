package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingReviewJapRepository extends JpaRepository<MatchingReview, Long> {
}
