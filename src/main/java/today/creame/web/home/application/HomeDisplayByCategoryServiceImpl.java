package today.creame.web.home.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import today.creame.web.home.application.model.DisplayParameter;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceResult;

@RequiredArgsConstructor
@Service
public class HomeDisplayByCategoryServiceImpl implements HomeDisplayService {
    private final Logger log = LoggerFactory.getLogger(HomeDisplayByCategoryServiceImpl.class);
    private final  InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(int page, int size, DisplayParameter parameter) {
        return influenceQuery.listByCategory(parameter.getCategory(), PageRequest.of(page, size, Sort.by(Order.desc("createdDateTime"))));
    }
}