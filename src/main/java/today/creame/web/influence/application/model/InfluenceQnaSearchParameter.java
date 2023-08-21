package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.entrypoint.rest.io.InfluenceQnaSearchRequest;

@Getter
public class InfluenceQnaSearchParameter {
    private Long memberId;
    private String memberNickname;

    private Long influenceId;
    private String influenceNickname;

    public InfluenceQnaSearchParameter(InfluenceQnaSearchRequest request) {
        this.memberId = request.getMemberId();
        this.memberNickname = request.getMemberNickname();
        this.influenceId = request.getInfluenceId();
        this.influenceNickname = request.getInfluenceNickname();
    }
}
