package today.creame.web.influence.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor(access = PROTECTED)
@Getter @ToString
@Embeddable
public class ReviewBlocked {

    private boolean blocked;
    private String blockedReason;
    private LocalDateTime blockedDateTime;

    public ReviewBlocked(boolean blocked, String blockedReason, LocalDateTime blockedDateTime) {
        this.blocked = blocked;
        this.blockedReason = blockedReason;
        this.blockedDateTime = blockedDateTime;
    }
}
