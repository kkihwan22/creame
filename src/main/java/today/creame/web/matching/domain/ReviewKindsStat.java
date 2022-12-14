package today.creame.web.matching.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.matching.domain.converter.ReviewKindsToStringConverter;

@NoArgsConstructor
@Entity
@Table(name = "review_kinds_stat")
@Getter
@ToString
public class ReviewKindsStat {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "influence_id")
    private Long influenceId;

    @Convert(converter = ReviewKindsToStringConverter.class)
    @Column(name = "kinds")
    private ReviewKinds kinds;

    @Column(name = "total")
    private int total;
}
