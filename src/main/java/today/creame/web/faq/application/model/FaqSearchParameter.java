package today.creame.web.faq.application.model;

import lombok.Getter;
import today.creame.web.faq.domain.FaqCategory;
import today.creame.web.faq.entrypoint.rest.io.FaqSearchRequest;

@Getter
public class FaqSearchParameter {
    private FaqCategory category;

    public FaqSearchParameter(FaqSearchRequest request) {
        this.category = request.getCategory();
    }
}
