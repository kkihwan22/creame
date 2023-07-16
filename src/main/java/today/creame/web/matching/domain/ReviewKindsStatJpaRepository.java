package today.creame.web.matching.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewKindsStatJpaRepository extends JpaRepository<ReviewKindsStat, Long> {

    List<ReviewKindsStat> findReviewKindsStatByInfluenceId(Long influenceId);
    Optional<ReviewKindsStat> findReviewKindsStatByInfluenceIdAndKinds(Long influenceId, ReviewKinds kinds);
}
