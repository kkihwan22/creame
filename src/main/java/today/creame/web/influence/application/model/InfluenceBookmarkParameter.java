package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceBookmark;
import today.creame.web.share.model.BaseParameter;

@Getter @ToString
public class InfluenceBookmarkParameter implements BaseParameter<InfluenceBookmark> {
    private final Long myId;
    private final Long influenceId;

    public InfluenceBookmarkParameter(Long myId, Long influenceId) {
        this.myId = myId;
        this.influenceId = influenceId;
    }

    @Override
    public InfluenceBookmark toEntity() {
        return new InfluenceBookmark(this.myId, this.influenceId, this.myId);
    }
}
