package today.creame.web.influence.application.model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceNotice;
import today.creame.web.influence.domain.InfluenceNoticeAttachFile;

@Getter
@ToString
public class InfluenceNoticeResult {
    private String content;
    private List<InfluenceNoticeAttachFile> attachFiles;

    public InfluenceNoticeResult(InfluenceNotice influenceNotice) {
        this.content = influenceNotice.getContent();
        this.attachFiles = influenceNotice.getAttachFiles();
    }
}
