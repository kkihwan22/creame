package today.creame.web.influence.domain;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InfluenceCategoryJpaRepository extends JpaRepository<InfluenceCategory, Long> {

    List<InfluenceCategory> findByIdIn(Set<Long> idSet);

    List<InfluenceCategory> findByInfluenceIdIn(Set<Long> idSet);

    List<InfluenceCategory> findByCategoryIs(Category category);
    List<InfluenceCategory> findByCategoryIs(Category category, Pageable pageable);

    @Query(value =
        " select new today.creame.web.influence.domain.InfluenceCategoryGroupByDTO(ic.category, count(ic))" +
            " from InfluenceCategory ic group by ic.category"
    )
    List<InfluenceCategoryGroupByDTO> groupByCount();
}
