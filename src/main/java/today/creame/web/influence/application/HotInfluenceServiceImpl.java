package today.creame.web.influence.application;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.HotInfluence;
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

    // 인플루언스 등록 !!

    @Transactional
    @Override
    public void create(HotInfluenceUpdateParameter parameter) {
        Long influenceId = parameter.getInfluenceId();
        Influence influence = influenceJpaRepository.findById(influenceId)
            .orElseThrow(NotFoundInfluenceException::new);

        String joinedCategories = influence.getCategories().stream()
            .map(InfluenceCategory::getCategory)
            .map(Category::name)
            .collect(Collectors.joining(","));

        HotInfluence hotInfluence = new HotInfluence(
            influenceId,
            parameter.getTitle(),
            parameter.getBannerImageUri(),
            influence.getNickname(),
            influence.getExtensionNumber(),
            joinedCategories,
            parameter.getOrderNumber());

        hotInfluenceJpaRepository.save(hotInfluence);
    }
}