package today.creame.web.faq.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.faq.application.model.FaqResult;
import today.creame.web.faq.domain.Faq;
import today.creame.web.faq.domain.FaqCategory;
import java.time.LocalDateTime;

@Getter
public class FaqResponse {
    private Long id;
    private String title;
    private String content;
    private FaqCategory category;
    private int orderNumber;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public FaqResponse(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.content = faq.getContent();
        this.category = faq.getCategory();
        this.orderNumber = faq.getOrderNumber();
        this.createdDt = faq.getCreatedDateTime();
        this.updatedDt = faq.getUpdatedDateTime();
    }

    public FaqResponse(FaqResult result) {
        this.id = result.getId();
        this.title = result.getTitle();
        this.content = result.getContent();
        this.category = result.getCategory();
        this.orderNumber = result.getOrderNumber();
        this.createdDt = result.getCreatedDt();
        this.updatedDt = result.getUpdatedDt();
    }
}
