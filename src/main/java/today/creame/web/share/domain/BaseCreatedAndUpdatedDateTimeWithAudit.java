package today.creame.web.share.domain;

import javax.persistence.Column;
import lombok.Getter;

public abstract class BaseCreatedAndUpdatedDateTimeWithAudit extends BaseCreatedAndUpdatedDateTime {

    @Column(name = "created_by")
    @Getter
    protected Long createdBy;

    @Column(name = "updated_by")
    @Getter
    protected Long updatedBy;


}
