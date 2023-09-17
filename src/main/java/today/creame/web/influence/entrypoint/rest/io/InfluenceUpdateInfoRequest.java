package today.creame.web.influence.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.creame.web.influence.domain.Rank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@AllArgsConstructor
public class InfluenceUpdateInfoRequest {
    private String name;
    private Rank rank;
    private List<SnsUpdateRequest> snsRequests;
    private boolean exposed;    //0 미노출, 1노출
    @NotNull
    private List<String> categories;
    private String introduction;
}
