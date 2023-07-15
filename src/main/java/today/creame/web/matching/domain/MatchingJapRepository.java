package today.creame.web.matching.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MatchingJapRepository extends JpaRepository<Matching, Long> {
    Optional<Matching> findMatchingByCallId(String callId);
    List<Matching> findMatchingsByStatus(MatchingProgressStatus matchingProgressStatus);

    @Query(value = "select " +
            "DATE_FORMAT(m.start_dt, '%Y%m') AS yearMonth," +
            "m.paid_type AS paidType," +
            "SUM(TIME_TO_SEC(TIMEDIFF(m.end_dt, m.start_dt))) AS totalTime," +
            "SUM(used_coins) as totalCoin " +
            "from matching m " +
            "where m.influence_id = :influenceId " +
            "  and m.progress = 'END' " +
            "  and DATE_FORMAT(m.start_dt, '%Y%m') >= :toDate " +
            "  and DATE_FORMAT(m.start_dt, '%Y%m') <= :fromDate " +
            "group by DATE_FORMAT(m.start_dt, '%Y%m'), m.paid_type " +
            "order by DATE_FORMAT(m.start_dt, '%Y%m') desc", nativeQuery = true)
    List<Object[]> getConsultationHoursPerMonth(Long influenceId, String toDate, String fromDate);
}
