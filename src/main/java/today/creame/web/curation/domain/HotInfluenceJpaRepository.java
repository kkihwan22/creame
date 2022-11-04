package today.creame.web.curation.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotInfluenceJpaRepository extends JpaRepository<HotInfluence, Long> {

}
