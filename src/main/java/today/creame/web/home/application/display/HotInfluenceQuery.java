package today.creame.web.home.application.display;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import today.creame.web.home.application.DisplayQuery;
import today.creame.web.home.entrypoint.io.HomeQueryParam;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HotInfluenceQuery implements DisplayQuery {
    private final InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(HomeQueryParam parameter) {
        Sort orders = Sort.by(
                Order.desc("reviewCount"),
                Order.desc("connected"),
                Order.desc("calling"),
                Order.desc("createdDateTime")
        );
        PageRequest pageRequest = parameter.getPageRequest().withSort(orders);
        return influenceQuery.listAll(pageRequest);
    }
}