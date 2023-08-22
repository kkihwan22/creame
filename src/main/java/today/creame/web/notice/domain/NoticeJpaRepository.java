package today.creame.web.notice.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeJpaRepository extends JpaRepository<Notice, Long> {

    Optional<Notice> findByIdAndDeletedIsFalse(Long id);

}
