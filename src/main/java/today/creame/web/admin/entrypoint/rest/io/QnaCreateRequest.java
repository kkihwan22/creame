package today.creame.web.admin.entrypoint.rest.io;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.admin.application.model.QnaCreateParameter;
import today.creame.web.admin.domain.QnaCategory;
import today.creame.web.share.model.BaseFileResource;
import today.creame.web.share.support.SecurityContextSupporter;

@Getter
@ToString
public class QnaCreateRequest {

    @NotNull
    private QnaCategory category;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private List<BaseFileResource> attachFiles;

    public QnaCreateParameter toParam() {
        return new QnaCreateParameter(SecurityContextSupporter.get().getId(), this.category, this.title, this.content, this.attachFiles);
    }
}
