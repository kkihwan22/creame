package today.creame.web.faq.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FaqJpaRepository extends JpaRepository<Faq, Long> {

    Optional<Faq> findByIdAndDeletedIsFalse(Long id);
    List<Faq> findAllByCategoryAndDeletedIsFalse(FaqCategory category);
}
