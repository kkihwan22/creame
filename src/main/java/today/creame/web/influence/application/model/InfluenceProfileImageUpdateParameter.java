package today.creame.web.influence.application.model;

import lombok.Getter;
import java.util.List;

@Getter
public class InfluenceProfileImageUpdateParameter {
    private Long influenceId;
    private List<Long> createFileResourceFileIds;
    private List<Long> deleteFileResourceFileIds;

    public InfluenceProfileImageUpdateParameter(Long influenceId, List<Long> createFileResourceFileIds, List<Long> deleteFileResourceFileIds) {
        this.influenceId = influenceId;
        this.createFileResourceFileIds = createFileResourceFileIds;
        this.deleteFileResourceFileIds = deleteFileResourceFileIds;
    }
}
