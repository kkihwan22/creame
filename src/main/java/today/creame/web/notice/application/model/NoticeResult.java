package today.creame.web.notice.application.model;

import lombok.Getter;
import today.creame.web.notice.domain.Notice;

import java.time.LocalDateTime;

@Getter
public class NoticeResult {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public NoticeResult(Notice notice) {
        this.id = notice.getId();
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdDt = notice.getCreatedDateTime();
        this.updatedDt = notice.getUpdatedDateTime();
    }
}
