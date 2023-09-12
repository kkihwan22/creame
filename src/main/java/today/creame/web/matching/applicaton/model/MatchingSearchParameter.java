package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import today.creame.web.matching.entrypoint.rest.io.MatchingSearchRequest;

import java.time.LocalDate;

@Getter
public class MatchingSearchParameter {
    private String type;
    private Long id;
    private String nickname;
    private LocalDate startDt;
    private LocalDate endDt;

    public MatchingSearchParameter(String type, MatchingSearchRequest request) {
        this.type = type;
        this.id = request.getId();
        this.nickname = request.getNickname();
        this.startDt = request.getStartDt();
        this.endDt = request.getEndDt();
    }
}
