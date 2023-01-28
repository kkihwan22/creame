package today.creame.web.admin.entrypoint.rest.io;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.admin.application.model.QnaCreateParameter;
import today.creame.web.admin.domain.QnaCategory;
import today.creame.web.share.entrypoint.validator.EnumConstraint;
import today.creame.web.share.model.BaseFileResource;

@Getter
@ToString
public class QnaCreateRequest {

    @EnumConstraint(enumClass = QnaCategory.class)
    private QnaCategory category;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private List<BaseFileResource> attachedFiles;

    public QnaCreateParameter toParam() {
        return new QnaCreateParameter(this.category, this.title, this.content, this.attachedFiles);
    }
}
