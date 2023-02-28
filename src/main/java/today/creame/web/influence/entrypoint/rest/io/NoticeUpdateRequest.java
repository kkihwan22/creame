package today.creame.web.influence.entrypoint.rest.io;

import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.model.BaseFileResource;

@Getter
@ToString
public class NoticeUpdateRequest {
    @NotBlank
    private String content;
    private List<BaseFileResource> attachFiles;
}
