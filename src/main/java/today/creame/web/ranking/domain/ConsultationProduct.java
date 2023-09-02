package today.creame.web.ranking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "consultation_product")
@DynamicInsert
@DynamicUpdate
@Getter
public class ConsultationProduct extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "ranking_id")
    private Long rankingId;

    @JoinColumn(name = "order_no")
    private Integer orderNo;

    @Column(name = "budget_amount")
    private Integer budgetAmount;

    @Column(name = "consultation_amount")
    private Integer consultationAmount;

    public ConsultationProduct(Ranking ranking, int budgetAmount, int consultationAmount) {
        this.rankingId = ranking.getId();
        this.budgetAmount = budgetAmount;
        this.consultationAmount = consultationAmount;
    }

    public void change(Ranking ranking, int budgetAmount, int consultationAmount) {
        this.rankingId = ranking.getId();
        this.budgetAmount = budgetAmount;
        this.consultationAmount = consultationAmount;
    }
}
