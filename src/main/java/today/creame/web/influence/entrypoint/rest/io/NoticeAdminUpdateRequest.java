package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.share.model.BaseFileResource;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@AllArgsConstructor
public class NoticeAdminUpdateRequest {
    @NotBlank
    private String content;
    private List<BaseFileResource> attachFiles;
}
