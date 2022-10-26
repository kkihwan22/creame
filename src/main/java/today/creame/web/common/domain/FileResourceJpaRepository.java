package today.creame.web.common.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileResourceJpaRepository extends JpaRepository<FileResource, Long> {

    List<FileResource> findFileResourceByIdIn(List<Long> ids);
}
