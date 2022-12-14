package today.creame.web.influence.domain;

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
import today.creame.web.influence.domain.converter.CategoryToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "influence_category")
@DynamicInsert
@DynamicUpdate
@Getter @ToString(exclude = {
    "influence"
})
public class InfluenceCategory extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(
        name = "influence_id",
        nullable = false
    )
    private Influence influence;

    @Convert(converter = CategoryToStringConverter.class)
    @Column(name = "category")
    private Category category;

    public InfluenceCategory(String categoryName) {
        this.category = Category.valueOf(categoryName);
    }


    public void addInfluence(Influence influence) {
        if (this.influence != null) {
            this.influence.getCategories().remove(this);
        }

        this.influence = influence;

        if (!influence.getCategories().contains(this)) {
            influence.addInfluenceCategory(this);
        }
    }
}
