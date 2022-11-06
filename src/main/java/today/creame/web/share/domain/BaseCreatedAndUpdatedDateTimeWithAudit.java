package today.creame.web.share.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
public abstract class BaseCreatedAndUpdatedDateTimeWithAudit extends BaseCreatedAndUpdatedDateTime {

    @Column(name = "created_by")
    @Getter
    protected Long createdBy;

    @Column(name = "updated_by")
    @Getter
    protected Long updatedBy;
}
