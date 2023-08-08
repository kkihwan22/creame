package today.creame.web.ranking.application.model;

import lombok.Getter;
import today.creame.web.ranking.domain.ConsultationProduct;
import today.creame.web.ranking.endpoint.rest.io.ConsultationProductRegisterRequest;
import today.creame.web.share.model.BaseParameter;

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
