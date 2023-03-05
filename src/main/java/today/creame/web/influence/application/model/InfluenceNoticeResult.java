package today.creame.web.influence.application.model;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceNotice;
import today.creame.web.share.model.BaseFileResource;

@Getter
@ToString
public class InfluenceNoticeResult {
    private String content;
    private List<BaseFileResource> attachFiles;

    public InfluenceNoticeResult(InfluenceNotice influenceNotice) {
        this.content = influenceNotice.getContent();
        this.attachFiles = influenceNotice.getAttachFiles().stream().map(it -> new BaseFileResource(it.getFileId(), it.getFileUri())).collect(Collectors.toList());
    }
}
