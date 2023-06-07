package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import today.creame.web.influence.domain.Influence;
import today.creame.web.member.domain.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {
    List<Matching> findMatchingsByInfluenceAndMemberAndStatusInAndStartDateTimeBetween(Influence influence, Member member, Set<MatchingProgressStatus> statusSet, LocalDateTime to, LocalDateTime from);

    @Query(value = "select " +
            "DATE_FORMAT(m.start_dt, '%Y%m') AS yearMonth," +
            "m.paid_type AS paidType," +
            "SEC_TO_TIME(SUM(TIME_TO_SEC(TIMEDIFF(m.end_dt, m.start_dt)))) AS totalTime," +
            "SUM(used_coins) as totalCoin " +
            "from matching m " +
            "where m.influence_id = :influenceId " +
            "  and m.progress = 'END' " +
            "  and DATE_FORMAT(m.start_dt, '%Y%m') >= :targetDate " +
            "group by DATE_FORMAT(m.start_dt, '%Y%m'), m.paid_type " +
            "order by DATE_FORMAT(m.start_dt, '%Y%m') desc", nativeQuery = true)
    List<Object[]> getConsultationHoursPerMonth(Long influenceId, String targetDate);
}
