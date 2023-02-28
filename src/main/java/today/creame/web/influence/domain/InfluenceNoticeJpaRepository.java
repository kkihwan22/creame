package today.creame.web.influence.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluenceNoticeJpaRepository extends JpaRepository<InfluenceNotice, Long> {

    List<InfluenceNotice> findInfluenceNoticesByInfluenceIdAndDeleted(Long influenceId, boolean deleted);
}
