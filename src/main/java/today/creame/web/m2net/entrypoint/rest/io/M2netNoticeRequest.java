package today.creame.web.m2net.entrypoint.rest.io;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.m2net.application.model.M2netNoticeCommand;
import today.creame.web.m2net.domain.DeductionMethod;
import today.creame.web.m2net.domain.M2netReasonCode;

@NoArgsConstructor
@Getter
@ToString
public class M2netNoticeRequest {
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:dd:ss";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(M2netNoticeRequest.DATE_FORMAT);

    private String cpid;
    private String csrid;
    private String dtmfno;
    private String start;
    private String end;
    private String eventtm;
    private String membid;
    private String from;
    private String to;
    private Integer usetm;
    private Integer amt;
    private String telno;
    private String reason;
    private String preflag;

    public M2netNoticeCommand of() {
        return new M2netNoticeCommand(
            this.toString(),
            this.csrid,
            this.membid,
            LocalDateTime.parse(this.start, FORMATTER),
            LocalDateTime.parse(this.end, FORMATTER),
            LocalDateTime.parse(this.eventtm, FORMATTER),
            usetm,
            amt,
            M2netReasonCode.valueOf(reason.toUpperCase()),
            DeductionMethod.valueOf(this.preflag)
        );
    }
}