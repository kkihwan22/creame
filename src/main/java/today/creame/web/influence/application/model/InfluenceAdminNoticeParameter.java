package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceNotice;
import today.creame.web.share.model.BaseFileResource;
import today.creame.web.share.model.BaseParameter;

import java.util.List;

@Getter
@ToString
public class InfluenceAdminNoticeParameter implements BaseParameter<InfluenceNotice> {
    private Long influenceId;
    private String content;
    private List<BaseFileResource> attachFiles;

    public InfluenceAdminNoticeParameter(Long influenceId, String content, List<BaseFileResource> attachFiles) {
        this.influenceId = influenceId;
        this.content = content;
        this.attachFiles = attachFiles;
    }

    @Override
    public InfluenceNotice toEntity() {
        return new InfluenceNotice(this.influenceId, this.content);
    }
}
