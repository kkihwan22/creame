package today.creame.web.ranking.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.ranking.domain.ConsultationProduct;


public interface ConsultationProductQuery {
    Page<ConsultationProduct> list(Pageable pageable);
}
