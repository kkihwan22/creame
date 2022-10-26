package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceProfileImage;

@Getter @ToString
public class InfluenceProfileImageFileResourceResult {
    private final Long id;
    private final String uri;
    private final boolean deleted;
    private final int orderNumber;

    public InfluenceProfileImageFileResourceResult(Long id, String uri, boolean deleted, int orderNumber) {
        this.id = id;
        this.uri = uri;
        this.deleted = deleted;
        this.orderNumber = orderNumber;
    }

    public InfluenceProfileImage toEntity() {
        return new InfluenceProfileImage(this.id, this.uri, this.deleted, this.orderNumber);
    }
}
