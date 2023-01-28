package today.creame.web.admin.application.model;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.admin.domain.QnaCategory;
import today.creame.web.share.model.BaseFileResource;

@NoArgsConstructor
@Getter
@ToString
public class QnaCreateParameter {

    private QnaCategory category;
    private String title;
    private String content;
    private List<BaseFileResource> attachedFiles;

    public QnaCreateParameter(QnaCategory category, String title, String content, List<BaseFileResource> attachedFiles) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.attachedFiles = attachedFiles;
    }
}
