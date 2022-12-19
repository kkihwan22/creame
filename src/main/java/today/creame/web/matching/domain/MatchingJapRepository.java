package today.creame.web.matching.domain;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {

    List<Matching> findMatchingsByMemberId(Long memberId, Sort sort);

    List<Matching> findMatchingsByInfluenceId(Long influenceId, Sort sort);
}
