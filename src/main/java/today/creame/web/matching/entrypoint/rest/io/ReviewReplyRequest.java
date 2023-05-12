package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter @ToString
public class ReviewReplyRequest {

    @NotBlank
    private String content;


}
