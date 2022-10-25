package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceProfileImage;

@RequiredArgsConstructor
@Service
public class InfluenceServiceImpl implements InfluenceService {
    private final Logger log = LoggerFactory.getLogger(InfluenceServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;

    @Transactional
    @Override
    public Long create(InfluenceCreateParameter parameter) {
        Influence influence = parameter.toEntity();
        log.debug("influence: {}", influence);

        parameter.getCategories().forEach(item -> {
            influence.addInfluenceCategory(new InfluenceCategory(item));
        });

        // todo: profileImages 등록 추가

        influenceJpaRepository.save(influence);
        log.debug("after save influence: {}", influence);

        return influence.getId();
    }
}
