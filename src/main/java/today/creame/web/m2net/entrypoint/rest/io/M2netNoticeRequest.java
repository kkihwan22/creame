package today.creame.web.m2net.entrypoint.rest.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class M2netNoticeRequest {

    @JsonProperty(value = "cpid")
    private String cpId;

    @JsonProperty(value = "csrid")
    private String counselorId;

    @JsonProperty(value = "dtmfNo")
    private String dtmfNo;

    @JsonProperty(value = "start")
    private String start;

    @JsonProperty(value = "end")
    private String end;

    @JsonProperty(value = "eventtm")
    private String eventTime;

    @JsonProperty(value = "memId")
    private String memberId;

    @JsonProperty(value = "from")
    private String from;

    @JsonProperty(value = "to")
    private String to;

    @JsonProperty(value = "usetm")
    private Integer usingSecond;

    @JsonProperty(value = "amt")
    private Integer amount;

    @JsonProperty(value = "telno")
    private String telno;

    @JsonProperty(value = "preflag")
    private String preFlag;

    @JsonProperty(value = "reason")
    private String reason;
}