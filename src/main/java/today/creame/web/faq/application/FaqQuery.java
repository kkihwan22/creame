package today.creame.web.faq.application;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import today.creame.web.faq.application.model.FaqSearchParameter;
import today.creame.web.faq.domain.Faq;

public interface FaqQuery {
    Page<Faq> list(FaqSearchParameter searchParameter, Pageable pageable);
}
