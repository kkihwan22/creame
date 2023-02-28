package today.creame.web.influence.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceNotice;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseFileResource;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class InfluenceNoticeParameter implements BaseParameter<InfluenceNotice> {
    @PermitRule(type = ME)
    private Long influenceId;
    private String content;
    private List<BaseFileResource> attachFiles;

    public InfluenceNoticeParameter(Long influenceId, String content, List<BaseFileResource> attachFiles) {
        this.influenceId = influenceId;
        this.content = content;
        this.attachFiles = attachFiles;
    }

    @Override
    public InfluenceNotice toEntity() {
        return new InfluenceNotice(this.influenceId, this.content);
    }
}
