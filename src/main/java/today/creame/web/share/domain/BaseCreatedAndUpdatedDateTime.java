package today.creame.web.share.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Getter;


@MappedSuperclass
public abstract class BaseCreatedAndUpdatedDateTime {

    @Column(name = "created_dt")
    @Getter
    protected LocalDateTime createdDateTime;

    @Column(name = "updated_dt")
    @Getter
    protected LocalDateTime updatedDateTime;
}