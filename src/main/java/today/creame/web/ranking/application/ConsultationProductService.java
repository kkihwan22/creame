package today.creame.web.ranking.application;

import today.creame.web.ranking.application.model.ConsultationProductRegisterParameter;
import today.creame.web.ranking.application.model.ConsultationProductResult;
import today.creame.web.ranking.application.model.ConsultationProductUpdateParameter;


public interface ConsultationProductService {
    ConsultationProductResult get(Long id);
    Long register(ConsultationProductRegisterParameter parameter);
    void update(ConsultationProductUpdateParameter parameter);
    void delete(Long id);
}
