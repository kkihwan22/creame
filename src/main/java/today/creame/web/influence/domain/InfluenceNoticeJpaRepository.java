package today.creame.web.influence.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluenceNoticeJpaRepository extends JpaRepository<InfluenceNotice, Long> {

    Optional<InfluenceNotice> findInfluenceNoticeByInfluenceId(Long influenceId);

    List<InfluenceNotice> findInfluenceNoticesByInfluenceIdAndDeleted(Long influenceId, boolean deleted);
}
