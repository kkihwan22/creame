package today.creame.web.ranking.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.ranking.application.model.ConsultationProductResult;
import today.creame.web.ranking.domain.ConsultationProduct;

import java.time.LocalDateTime;

@Getter
public class ConsultationProductResponse {
    private Long id;
    private String description;
    private String qualifications;
    private int budgetAmounts;
    private int consultationAmount;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public ConsultationProductResponse(ConsultationProduct consultationProduct) {
        this.id = consultationProduct.getId();
        this.description = "";
        this.qualifications = "";
        this.budgetAmounts = consultationProduct.getBudgetAmount();
        this.consultationAmount = consultationProduct.getConsultationAmount();
        this.createdDt = consultationProduct.getCreatedDateTime();
        this.updatedDt = consultationProduct.getUpdatedDateTime();
    }

    public ConsultationProductResponse(ConsultationProductResult result) {
        this.id = result.getId();
        this.description = result.getDescription();
        this.qualifications = result.getQualifications();
        this.budgetAmounts = result.getBudgetAmounts();
        this.consultationAmount = result.getConsultationAmount();
        this.createdDt = result.getCreatedDt();
        this.updatedDt = result.getUpdatedDt();
    }

}
