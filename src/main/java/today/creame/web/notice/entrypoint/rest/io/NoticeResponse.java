package today.creame.web.notice.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.notice.application.model.NoticeResult;
import today.creame.web.notice.domain.Notice;

import java.time.LocalDateTime;

@Getter
public class NoticeResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public NoticeResponse(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdDt = notice.getCreatedDateTime();
        this.updatedDt = notice.getUpdatedDateTime();
    }

    public NoticeResponse(NoticeResult result) {
        this.id = result.getId();
        this.title = result.getTitle();
        this.content = result.getContent();
        this.createdDt = result.getCreatedDt();
        this.updatedDt = result.getUpdatedDt();
    }
}
