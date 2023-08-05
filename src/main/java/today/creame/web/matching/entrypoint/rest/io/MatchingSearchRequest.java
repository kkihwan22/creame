package today.creame.web.matching.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MatchingSearchRequest {
    private Long id;
    private String nickname;
}
