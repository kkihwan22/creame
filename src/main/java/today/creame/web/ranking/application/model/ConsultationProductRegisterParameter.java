package today.creame.web.ranking.application.model;

import lombok.Getter;
import today.creame.web.ranking.entrypoint.rest.io.ConsultationProductRegisterRequest;

@Getter
public class ConsultationProductRegisterParameter {
    private Long rankingId;
    private int budgetAmounts;
    private int consultationAmount;

    public ConsultationProductRegisterParameter(ConsultationProductRegisterRequest request) {
        this.rankingId = request.getRankingId();
        this.budgetAmounts = request.getBudgetAmounts();
        this.consultationAmount = request.getConsultationAmount();
    }
}
