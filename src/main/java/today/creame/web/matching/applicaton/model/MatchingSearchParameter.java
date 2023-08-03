package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import today.creame.web.matching.entrypoint.rest.io.MatchingSearchRequest;

@Getter
public class MatchingSearchParameter {
    private String type;
    private Long id;
    private String nickname;

    public MatchingSearchParameter(String type, MatchingSearchRequest request) {
        this.type = type;
        this.id = request.getId();
        this.nickname = request.getNickname();
    }
}
