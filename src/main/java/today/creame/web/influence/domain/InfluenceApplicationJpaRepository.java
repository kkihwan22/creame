package today.creame.web.influence.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluenceApplicationJpaRepository extends JpaRepository<InfluenceApplication, Long> {

    List<InfluenceApplication> findInfluenceApplicationByStatusIn(List<InfluenceApplicationStatus> statuses);
}
