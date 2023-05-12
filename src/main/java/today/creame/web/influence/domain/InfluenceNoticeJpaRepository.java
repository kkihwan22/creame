package today.creame.web.influence.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InfluenceNoticeJpaRepository extends JpaRepository<InfluenceNotice, Long> {

    Optional<InfluenceNotice> findFirstByInfluenceIdAndDeleted(Long influenceId, boolean deleted);

    List<InfluenceNotice> findInfluenceNoticesByInfluenceIdAndDeleted(Long influenceId, boolean deleted);
}
