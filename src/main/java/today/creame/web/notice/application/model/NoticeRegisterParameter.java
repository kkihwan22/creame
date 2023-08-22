package today.creame.web.notice.application.model;

import lombok.Getter;
import today.creame.web.notice.domain.Notice;
import today.creame.web.notice.entrypoint.rest.io.NoticeRegisterRequest;
import today.creame.web.share.model.BaseParameter;


@Getter
public class NoticeRegisterParameter implements BaseParameter<Notice> {
    private Long id;
    private String title;
    private String content;

    public NoticeRegisterParameter(NoticeRegisterRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    public NoticeRegisterParameter(Long id, NoticeRegisterRequest request) {
        this.id = id;
        this.title = request.getTitle();
        this.content = request.getContent();
    }

    @Override
    public Notice toEntity() {
        return new Notice(this.title, this.content);
    }
}
