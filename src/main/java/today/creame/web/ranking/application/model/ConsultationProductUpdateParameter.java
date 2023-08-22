package today.creame.web.ranking.application.model;

import lombok.Getter;
import today.creame.web.ranking.entrypoint.rest.io.ConsultationProductRegisterRequest;

@Getter
public class ConsultationProductUpdateParameter {
    private Long consultationProductId;
    private Long rankingId;
    private int budgetAmounts;
    private int consultationAmount;

    public ConsultationProductUpdateParameter(Long consultationProductId, ConsultationProductRegisterRequest request) {
        this.consultationProductId = consultationProductId;
        this.rankingId = request.getRankingId();
        this.budgetAmounts = request.getBudgetAmounts();
        this.consultationAmount = request.getConsultationAmount();
    }
}
