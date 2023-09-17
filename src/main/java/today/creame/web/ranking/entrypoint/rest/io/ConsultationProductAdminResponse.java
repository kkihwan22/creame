package today.creame.web.ranking.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.ranking.domain.ConsultationProduct;

@Getter
public class ConsultationProductAdminResponse {
    private Long id;
    private Integer orderNo;
    private int budgetAmount;


    public ConsultationProductAdminResponse(ConsultationProduct consultationProduct) {
        this.id = consultationProduct.getId();
        this.orderNo = consultationProduct.getOrderNo();
        this.budgetAmount = consultationProduct.getBudgetAmount();
    }

}
