package today.creame.web.matching.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.matching.domain.converter.ReviewKindsToStringConverter;

@NoArgsConstructor(access = PROTECTED)
@Embeddable
@Getter
@ToString
public class Review {

    @Column(name = "rate")
    private int rate;

    @Column(name = "liked")
    private int likedCount;

    @Column(name = "category")
    private String category;

    @Convert(converter = ReviewKindsToStringConverter.class)
    @Column(name = "kinds")
    private ReviewKinds reviewKinds;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "write_dt")
    private LocalDateTime writeDateTime;

    @Column(name = "reply", columnDefinition = "text")
    private String reply;

    @Column(name = "reply_dt")
    private LocalDateTime replyDateTime;
}
