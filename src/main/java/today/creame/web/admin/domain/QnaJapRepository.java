package today.creame.web.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaJapRepository extends JpaRepository<Qna, Long> {
}
