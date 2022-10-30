package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class NoticeUpdateRequest {
    @NotBlank
    private String notice;
}
