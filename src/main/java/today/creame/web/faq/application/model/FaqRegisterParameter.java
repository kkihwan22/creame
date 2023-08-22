package today.creame.web.faq.application.model;

import lombok.Getter;
import today.creame.web.faq.domain.Faq;
import today.creame.web.faq.domain.FaqCategory;
import today.creame.web.faq.entrypoint.rest.io.FaqRegisterRequest;
import today.creame.web.share.model.BaseParameter;


@Getter
public class FaqRegisterParameter implements BaseParameter<Faq> {
    private Long id;
    private String title;
    private String content;
    private FaqCategory category;
    private int orderNumber;

    public FaqRegisterParameter(FaqRegisterRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.category = request.getCategory();
        this.orderNumber = request.getOrderNumber();
    }

    public FaqRegisterParameter(Long id, FaqRegisterRequest request) {
        this.id = id;
        this.title = request.getTitle();
        this.content = request.getContent();
        this.category = request.getCategory();
        this.orderNumber = request.getOrderNumber();
    }

    @Override
    public Faq toEntity() {
        return new Faq(this.title, this.content, this.category, this.orderNumber);
    }
}
