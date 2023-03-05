package today.creame.web.matching.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.creame.web.influence.domain.Influence;
import today.creame.web.member.domain.Member;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {

    List<Matching> findMatchingsByMemberId(Long memberId, Sort sort);

    List<Matching> findMatchingsByInfluenceId(Long influenceId, Sort sort);

    Optional<Matching> findMatchingByInfluenceAndMemberAndStatusAndStartDateTime(Influence influence, Member member, MatchingProgressStatus status, LocalDateTime startDateTime);
}
