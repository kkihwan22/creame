package today.creame.web.faq.application.model;

import lombok.Getter;
import today.creame.web.faq.domain.Faq;
import today.creame.web.faq.domain.FaqCategory;

import java.time.LocalDateTime;

@Getter
public class FaqResult {
    private Long id;
    private String title;
    private String content;
    private FaqCategory category;
    private int orderNumber;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public FaqResult(Faq faq) {
        this.id = faq.getId();
        this.title = faq.getTitle();
        this.content = faq.getContent();
        this.category = faq.getCategory();
        this.orderNumber = faq.getOrderNumber();
        this.createdDt = faq.getCreatedDateTime();
        this.updatedDt = faq.getUpdatedDateTime();
    }
}
