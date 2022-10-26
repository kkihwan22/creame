package today.creame.web.common.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "file_resource")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class FileResource extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "location")
    private String objectKey;

    @Column(name = "original_filename")
    private String originalFileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "file_size")
    private Long fileSize;

    @Column(name = "deleted")
    private Boolean deleted;

    public FileResource(String objectKey, String originalFileName, String contentType, Long fileSize) {
        this.objectKey = objectKey;
        this.originalFileName = originalFileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }
}
