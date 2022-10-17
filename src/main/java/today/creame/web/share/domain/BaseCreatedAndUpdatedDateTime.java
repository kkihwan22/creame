package today.creame.web.share.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;


@MappedSuperclass
public abstract class BaseCreatedAndUpdatedDateTime {

    @Column(name = "created_dt")
    @Getter
    protected LocalDateTime createdDateTime;

    @Column(name = "updated_dt")
    @Getter
    protected LocalDateTime updatedDateTime;
}