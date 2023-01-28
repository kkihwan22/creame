package today.creame.web.admin.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.INFLUENCE;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.admin.domain.QnaCategory;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseFileResource;

@NoArgsConstructor
@Getter
@ToString
public class QnaCreateParameter {
    @PermitRule(type = INFLUENCE)
    private Long influenceId;
    private QnaCategory category;
    private String title;
    private String content;
    private List<BaseFileResource> attachedFiles;

    public QnaCreateParameter(Long influenceId, QnaCategory category, String title, String content, List<BaseFileResource> attachedFiles) {
        this.influenceId = influenceId;
        this.category = category;
        this.title = title;
        this.content = content;
        this.attachedFiles = attachedFiles;
    }
}
