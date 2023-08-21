package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InfluenceQnaSearchRequest {
    private Long memberId;
    private String memberNickname;

    private Long influenceId;
    private String influenceNickname;
}
