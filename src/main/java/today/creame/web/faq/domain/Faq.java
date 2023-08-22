package today.creame.web.faq.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTimeWithAudit;

import javax.persistence.*;
import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "faq")
@DynamicInsert
@DynamicUpdate
@Getter
public class Faq extends BaseCreatedAndUpdatedDateTimeWithAudit {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private FaqCategory category;

    @Column(name = "order_no")
    private int orderNumber;

    @Column(name = "deleted")
    private boolean deleted;

    public Faq(String title, String content, FaqCategory category, int orderNumber) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.orderNumber = orderNumber;

    }

    public void changeFaq(String title, String content, FaqCategory category, int orderNumber) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.orderNumber = orderNumber;
    }

    public void changeOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void delete() {
        this.deleted = true;
    }
}