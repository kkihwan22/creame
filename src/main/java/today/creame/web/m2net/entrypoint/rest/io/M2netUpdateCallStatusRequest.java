package today.creame.web.m2net.entrypoint.rest.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import today.creame.web.m2net.application.model.M2netUpdateCallStatusCommand;
import today.creame.web.m2net.domain.DeductionMethod;
import today.creame.web.m2net.domain.M2netReasonCode;

@NoArgsConstructor
@Getter
@ToString
public class M2netUpdateCallStatusRequest {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(M2netUpdateCallStatusRequest.DATE_FORMAT);

    private String cpid;
    private String csrid;
    private String dtmfno;
    private String start;
    private String end;
    private String eventtm;
    private String membid;
    private String from;
    private String to;
    private int usetm;
    private int amt;
    private String telno;
    private String reason;
    private String preflag;

    public M2netUpdateCallStatusCommand of() {
        return new M2netUpdateCallStatusCommand(
            this.toString(),
            emptyToNull(this.csrid),
            emptyToNull(this.membid),
            datetimeIfNotNull(emptyToNull(this.start)),
            datetimeIfNotNull(emptyToNull(this.end)),
            datetimeIfNotNull(emptyToNull(this.eventtm)),
            usetm,
            amt,
            M2netReasonCode.valueOf(reason.toUpperCase()),
            DeductionMethod.get(this.preflag)
        );
    }

    private String emptyToNull(String s) {
        return StringUtils.isEmpty(s) ? null : s;
    }

    private LocalDateTime datetimeIfNotNull(String s) {
        return s != null ? LocalDateTime.parse(s, FORMATTER) : null;
    }
}