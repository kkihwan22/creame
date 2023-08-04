package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import today.creame.web.matching.entrypoint.rest.io.ReviewSearchRequest;

@Getter
public class ReviewSearchParameter {
    private Long memberId;
    private String nickname;

    public ReviewSearchParameter(ReviewSearchRequest request) {
        this.memberId = request.getMemberId();
        this.nickname = request.getNickname();
    }
}
