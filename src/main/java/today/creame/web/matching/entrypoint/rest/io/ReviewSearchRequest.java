package today.creame.web.matching.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReviewSearchRequest {
    private Long memberId;
    private String nickname;
}
