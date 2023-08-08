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

    @ManyToOne
    @JoinColumn(name = "ranking_id")
    private Ranking ranking;

    @Column(name = "budget_amount")
    private int budgetAmount;

    @Column(name = "consultation_amount")
    private int consultationAmount;

    public ConsultationProduct(Ranking ranking, int budgetAmount, int consultationAmount) {
        this.ranking = ranking;
        this.budgetAmount = budgetAmount;
        this.consultationAmount = consultationAmount;
    }

    public void change(Ranking ranking, int budgetAmount, int consultationAmount) {
        this.ranking = ranking;
        this.budgetAmount = budgetAmount;
        this.consultationAmount = consultationAmount;
    }
}
