package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class NoticeGetResponse {
    private String notice;

    public NoticeGetResponse(String notice) {
        this.notice = notice;
    }
}
