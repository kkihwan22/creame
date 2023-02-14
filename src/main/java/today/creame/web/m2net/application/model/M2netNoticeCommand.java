package today.creame.web.m2net.application.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEventPublisher;
import today.creame.web.m2net.domain.DeductionMethod;
import today.creame.web.m2net.domain.M2netNoticeHistory;
import today.creame.web.m2net.domain.M2netReasonCode;

@Getter
@ToString
public class M2netNoticeCommand {

    private String telegram;
    private String cId;
    private String mId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime eventDateTime;
    private Integer usedTime;
    private Integer usedAmount;
    private M2netReasonCode reason;
    private DeductionMethod deductionMethod;

    public M2netNoticeCommand(
        String telegram,
        String cId,
        String mId,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        LocalDateTime eventDateTime,
        Integer usedTime,
        Integer usedAmount,
        M2netReasonCode reason,
        DeductionMethod deductionMethod
    ) {
        this.telegram = telegram;
        this.cId = cId;
        this.mId = mId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.eventDateTime = eventDateTime;
        this.usedTime = usedTime;
        this.usedAmount = usedAmount;
        this.reason = reason;
        this.deductionMethod = deductionMethod;
    }

    public M2netNoticeHistory toEntity() {
        return new M2netNoticeHistory(
            this.telegram, this.cId, Long.parseLong(this.mId), this.reason, this.endDateTime
        );
    }

    public boolean isNotify(ApplicationEventPublisher publisher) {

        // publisher.publishEvent();
        return reason.isPublished();
    }
}
