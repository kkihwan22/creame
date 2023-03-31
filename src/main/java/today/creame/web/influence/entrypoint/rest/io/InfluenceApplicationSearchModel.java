package today.creame.web.influence.entrypoint.rest.io;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import today.creame.web.influence.domain.InfluenceApplicationStatus;

@Getter
@ToString
public class InfluenceApplicationSearchModel {
    private int page = 0;
    private int size = 20;
    private String statusList;

    public List<InfluenceApplicationStatus> getStatusList() {
        if (StringUtils.isNotEmpty(statusList)) {
            return Arrays.stream(statusList.split(",")).map(status -> InfluenceApplicationStatus.valueOf(status)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public PageRequest pageRequest() {
        return PageRequest.of(page, size);
    }
}
