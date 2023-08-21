package today.creame.web.matching.applicaton.model;

import lombok.Getter;
import today.creame.web.matching.entrypoint.rest.io.ReviewSearchRequest;

@Getter
public class ReviewSearchParameter {
    private Long memberId;
    private String memberNickname;

    private Long influenceId;
    private String influenceNickname;

    public ReviewSearchParameter(ReviewSearchRequest request) {
        this.memberId = request.getMemberId();
        this.memberNickname = request.getMemberNickname();
        this.influenceId = request.getInfluenceId();
        this.influenceNickname = request.getInfluenceNickname();
    }
}
