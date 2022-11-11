package today.creame.web.influence.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfluenceQnaJpaRepository extends JpaRepository<InfluenceQna, Long>  {

    Page<InfluenceQna> findByQuestioner_Id(Long id, Pageable pageable);
}
