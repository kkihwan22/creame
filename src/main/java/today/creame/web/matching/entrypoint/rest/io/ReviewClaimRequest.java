package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.ReviewClaimKinds;

@Getter @ToString
public class ReviewClaimRequest {

    private ReviewClaimKinds kinds;
    private String reason;
}
