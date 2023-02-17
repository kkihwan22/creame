package today.creame.web.home.application.display;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import today.creame.web.home.application.DisplayQuery;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;

;

@RequiredArgsConstructor
@Service
public class InfluenceQueryByRecommend implements DisplayQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryByRecommend.class);
    private final InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(HomeQueryParam parameter) {
        Sort orders = Sort.by(
            Order.desc("rate"),
            Order.desc("createdDateTime")
        );
        PageRequest pageRequest = parameter.getPageRequest().withSort(orders);
        return influenceQuery.listAll(pageRequest);
    }
}