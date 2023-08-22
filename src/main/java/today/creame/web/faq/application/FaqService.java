package today.creame.web.faq.application;

import today.creame.web.faq.application.model.FaqRegisterParameter;
import today.creame.web.faq.application.model.FaqResult;

public interface FaqService {
    FaqResult getDetail(Long id);
    void register(FaqRegisterParameter parameter);
    void update(FaqRegisterParameter parameter);
    void delete(Long id);
}
