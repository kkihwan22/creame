package today.creame.web.matching.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = PROTECTED)
@Getter
@ToString
@Embeddable
public class ReviewBlocked {

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "blocked_reason")
    private String blockedReason;

    @Column(name = "blocked_dt")
    private LocalDateTime blockedDateTime;

    public ReviewBlocked(boolean blocked, String blockedReason, LocalDateTime blockedDateTime) {
        this.blocked = blocked;
        this.blockedReason = blockedReason;
        this.blockedDateTime = blockedDateTime;
    }
}
