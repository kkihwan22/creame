package today.creame.web.ranking.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.ranking.domain.ConsultationProduct;

import java.util.List;


public interface ConsultationProductQuery {
    Page<ConsultationProduct> list(Pageable pageable);
    List<ConsultationProduct> listByAdmin();
}
