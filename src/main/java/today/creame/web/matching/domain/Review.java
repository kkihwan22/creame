package today.creame.web.matching.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Column(name = "kinds")
    private ReviewKinds reviewKinds;

    @Column(name = "content")
    private String content;

    @Column(name = "write_dt")
    private LocalDateTime writeDateTime;

    @Column(name = "reply")
    private String reply;

    @Column(name = "reply_at")
    private LocalDateTime replyDateTime;
}
