package today.creame.web.influence.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.influence.application.model.InfluenceProfileImageFileResourceResult;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceCategoryJpaRepository;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceProfileImageJpaRepository;
import today.creame.web.influence.exception.ConflictConnectionStatusException;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.share.domain.OnOffCondition;

@RequiredArgsConstructor
@Service
public class InfluenceServiceImpl implements InfluenceService {
    private final Logger log = LoggerFactory.getLogger(InfluenceServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;
    private final InfluenceProfileFileResourceQuery influenceProfileFileResourceQuery;

    @Transactional
    @Override
    public Long create(InfluenceCreateParameter parameter) {
        Influence influence = influenceJpaRepository.save(parameter.toEntity());
        log.debug("influence: {}", influence);

        for (String item : parameter.getCategories()) {
            influence.addInfluenceCategory(new InfluenceCategory(item));
        }

        influenceCategoryJpaRepository.saveAll(influence.getCategories());

        List<InfluenceProfileImageFileResourceResult> resultInfluenceProfileImages
            = influenceProfileFileResourceQuery.findInfluenceProfileImages(parameter.getProfileImages());

        for (InfluenceProfileImageFileResourceResult image : resultInfluenceProfileImages) {
            influence.addInfluenceProfileImage(image.toEntity());
        }

        influenceProfileImageJpaRepository.saveAll(influence.getProfileImages());

        return influence.getId();
    }

    @Transactional
    @Override
    public void patchConnectionStatus(Long id, OnOffCondition status) {

        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("find influence:{}", influence);

        if (status == OnOffCondition.ON && influence.isConnected()) {
            throw new ConflictConnectionStatusException();
        }

        if (status == OnOffCondition.OFF && !influence.isConnected()) {
            throw new ConflictConnectionStatusException();
        }

        influence.updateConnect();
    }
}
