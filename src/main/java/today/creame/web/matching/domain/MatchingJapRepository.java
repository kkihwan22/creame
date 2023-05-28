package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.creame.web.influence.domain.Influence;
import today.creame.web.member.domain.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {
    List<Matching> findMatchingsByInfluenceAndMemberAndStatusInAndStartDateTimeBetween(Influence influence, Member member, Set<MatchingProgressStatus> statusSet, LocalDateTime to, LocalDateTime from);

}
