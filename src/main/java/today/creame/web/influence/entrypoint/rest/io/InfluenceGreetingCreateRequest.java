package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class InfluenceGreetingCreateRequest {
    @NotNull
    private Long fileId;

    @NotBlank
    private String fileUri;
}
