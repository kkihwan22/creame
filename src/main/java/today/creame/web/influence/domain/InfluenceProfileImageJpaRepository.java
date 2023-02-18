package today.creame.web.influence.domain;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfluenceProfileImageJpaRepository extends JpaRepository<InfluenceProfileImage, Long> {

    List<InfluenceProfileImage> findByIdIn(Set<Long> idSet);

    List<InfluenceProfileImage> findByInfluence_IdIn(Set<Long> idSet);
}
