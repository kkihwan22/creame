package today.creame.web.influence.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluenceBookmarkJpaRepository extends JpaRepository<InfluenceBookmark, Long> {

    Long countByMemberIdAndInfluenceIdAndBookmarked(Long memberId, Long InfluenceId, boolean bookmarked);

    List<InfluenceBookmark> findInfluenceBookmarksByMemberIdAndBookmarked(Long memberId, boolean bookmarked);

    Optional<InfluenceBookmark> findByMemberIdAndInfluenceId(Long memberId, Long InfluenceId);
}
