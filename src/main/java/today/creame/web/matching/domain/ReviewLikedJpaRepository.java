package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface ReviewLikedJpaRepository extends JpaRepository<ReviewLiked, Long> {

    Optional<ReviewLiked> findReviewLikedByReviewIdAndMemberId(Long reviewId, Long memberId);
    List<ReviewLiked> findReviewLikesByReviewIdInAndMemberId(Set<Long> reviewIdSet, Long memberId);

}
