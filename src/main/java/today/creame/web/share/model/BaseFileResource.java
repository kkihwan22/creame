package today.creame.web.share.model;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class BaseFileResource {
    private Long fileId;

    @NotBlank
    private String fileUri;

    public BaseFileResource(Long fileId, String fileUri) {
        this.fileId = fileId;
        this.fileUri = fileUri;
    }
}
