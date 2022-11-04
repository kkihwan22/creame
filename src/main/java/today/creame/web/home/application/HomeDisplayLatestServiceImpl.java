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

;

@RequiredArgsConstructor
@Service
public class HomeDisplayLatestServiceImpl implements HomeDisplayService {
    private final Logger log = LoggerFactory.getLogger(HomeDisplayLatestServiceImpl.class);
    private final  InfluenceQuery influenceQuery;

    @Override
    public List<InfluenceResult> list(int page, int size, DisplayParameter parameter) {
        // todo: 한달로 기간을 제한하여야 한다.
        return influenceQuery.listAll(PageRequest.of(page, size, Sort.by(Order.desc("createdDateTime"))));
    }
}