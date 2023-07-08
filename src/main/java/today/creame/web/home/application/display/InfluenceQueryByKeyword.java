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
import today.creame.web.influence.domain.Category;

@RequiredArgsConstructor
@Service
public class InfluenceQueryByKeyword implements DisplayQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryByKeyword.class);
    private final InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(HomeQueryParam parameter) {
        Category category = Category.valueOfLabel(parameter.getKeyword());
        Sort orders = Sort.by(Order.desc("createdDateTime"));
        PageRequest pageRequest = parameter.getPageRequest().withSort(orders);

        // TODO: nickname + category 검색
        return influenceQuery.listByKeyword(category, parameter.getKeyword(), pageRequest);
    }
}