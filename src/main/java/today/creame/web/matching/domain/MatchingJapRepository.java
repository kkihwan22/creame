package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.creame.web.influence.domain.Influence;
import today.creame.web.member.domain.Member;

import java.util.Optional;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {
    Optional<Matching> findMatchingByInfluenceAndMemberAndStatus(Influence influence, Member member, MatchingProgressStatus status);
}
