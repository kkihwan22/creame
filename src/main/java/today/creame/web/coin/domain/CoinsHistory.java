package today.creame.web.coin.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.coin.domain.converter.CoinsHistoryTypeToString;
import today.creame.web.member.domain.Member;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "coins_history")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString
public class CoinsHistory extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Convert(converter = CoinsHistoryTypeToString.class)
    @Column(name = "type")
    private CoinsHistoryType type;

    @Column(name = "coins")
    private int coins;

    @Column(name = "balance_coins")
    private int balanceCoins;

    public CoinsHistory(Member member, CoinsHistoryType type, int coins) {
        this.member = member;
        this.type = type;
        this.coins = coins;
        this.balanceCoins = member.getCoins();
    }
}
