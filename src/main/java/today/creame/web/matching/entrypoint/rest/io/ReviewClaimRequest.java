package today.creame.web.matching.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.matching.domain.ReviewClaimKinds;
import today.creame.web.matching.domain.ReviewClaimStatus;

@Getter @ToString
public class ReviewClaimRequest {

    private ReviewClaimKinds kinds;
    private String comment;
}
