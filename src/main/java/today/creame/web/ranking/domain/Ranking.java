package today.creame.web.ranking.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "ranking")
@DynamicInsert
@DynamicUpdate
@Getter
public class Ranking extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "level")
    private int level;

    @Column(name = "description")
    private String description;

    @Column(name = "qualifications")
    private String qualifications;

    @OneToMany(mappedBy = "ranking")
    List<ConsultationProduct> consultationProducts = new ArrayList<>();
}
