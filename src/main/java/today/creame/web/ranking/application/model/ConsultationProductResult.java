package today.creame.web.ranking.application.model;

import lombok.Getter;
import today.creame.web.ranking.domain.ConsultationProduct;

import java.time.LocalDateTime;

@Getter
public class ConsultationProductResult {
    private Long id;
    private String description;
    private String qualifications;
    private int budgetAmounts;
    private int consultationAmount;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public ConsultationProductResult(ConsultationProduct consultationProduct) {
        this.id = consultationProduct.getId();
        this.description = "";
        this.qualifications = "";
        this.budgetAmounts = consultationProduct.getBudgetAmount();
        this.consultationAmount = consultationProduct.getConsultationAmount();
        this.createdDt = consultationProduct.getCreatedDateTime();
        this.updatedDt = consultationProduct.getUpdatedDateTime();
    }

}
