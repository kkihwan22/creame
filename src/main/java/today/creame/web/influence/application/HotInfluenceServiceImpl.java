package today.creame.web.influence.application;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.HotInfluenceJpaRepository;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;

@RequiredArgsConstructor
@Service
public class HotInfluenceServiceImpl implements HotInfluenceService {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;

    @Transactional
    @Override
    public void update(HotInfluenceUpdateParameter parameter) {
        Long id = parameter.getId();
        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);

        String joinedCategories = influence.getCategories().stream()
            .map(InfluenceCategory::getCategory)
            .map(Category::name)
            .collect(Collectors.joining(","));

//        HotInfluence hotInfluence = new HotInfluence(
//            id,
//            influence.getExtensionNumber(),
//            influence.getNickname(),
//            parameter.getTitle(),
//            parameter.getBannerImageUri(),
//            joinedCategories,
//            parameter.getOrderNumber());

//        hotInfluenceJpaRepository.save(hotInfluence);
    }
}