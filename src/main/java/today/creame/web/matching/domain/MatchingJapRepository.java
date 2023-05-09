package today.creame.web.matching.domain;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import today.creame.web.influence.domain.Influence;
import today.creame.web.member.domain.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {
    List<Matching> findMatchingByMember_IdAndCreatedDateTimeAfter(Long memberId, LocalDateTime since, Sort sort);
    Optional<Matching> findMatchingByInfluenceAndMemberAndStatus(Influence influence, Member member, MatchingProgressStatus status);
}
