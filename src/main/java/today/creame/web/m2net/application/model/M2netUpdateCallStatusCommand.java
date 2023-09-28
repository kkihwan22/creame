package today.creame.web.m2net.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.domain.DeductionMethod;
import today.creame.web.m2net.domain.M2netReasonCode;
import today.creame.web.matching.domain.PaidType;

import java.time.LocalDateTime;

@Getter
@ToString
public class M2netUpdateCallStatusCommand {
    private String telegram;
    private String callId;
    private String cId;
    private String mId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private LocalDateTime eventDateTime;
    private Integer usedTime;
    private Integer usedAmount;
    private M2netReasonCode reason;
    private DeductionMethod deductionMethod;
    private PaidType paidType;

    public M2netUpdateCallStatusCommand(
        String telegram,
        String callId,
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
        this.callId = callId;
        this.cId = cId;
        this.mId = mId;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.eventDateTime = eventDateTime;
        this.usedTime = usedTime;
        this.usedAmount = usedAmount;
        this.reason = reason;
        this.deductionMethod = deductionMethod;
        this.paidType = deductionMethod.getPaidType();
    }

    public boolean isDeferred() {
        return DeductionMethod.POST == deductionMethod;
    }
}
