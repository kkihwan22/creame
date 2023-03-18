package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.influence.application.model.InfluenceItemParameter;
import today.creame.web.influence.application.model.SnsParameter;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceCategoryJpaRepository;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceProfileImageJpaRepository;
import today.creame.web.influence.domain.Item;
import today.creame.web.influence.domain.SNS;
import today.creame.web.influence.exception.ConflictConnectionStatusException;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.m2net.infra.feign.M2netCounselorClient;
import today.creame.web.m2net.infra.feign.io.M2netCounselorCreateRequest;
import today.creame.web.share.aspect.permit.Permit;
import today.creame.web.share.domain.OnOffCondition;
import today.creame.web.share.event.ConnectionUpdateEvent;

@RequiredArgsConstructor
@Service
public class InfluenceServiceImpl implements InfluenceService {
    private final Logger log = LoggerFactory.getLogger(InfluenceServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;
    private final InfluenceProfileFileResourceQuery influenceProfileFileResourceQuery;
    private final ApplicationEventPublisher publisher;
    private final M2netCounselorClient client;

    @Transactional
    @Override
    public Long create(InfluenceCreateParameter parameter) {
        Influence influence = influenceJpaRepository.save(parameter.toEntity());
        log.debug("influence: {}", influence);

        parameter.getCategories().stream().forEach(it -> {
            InfluenceCategory category = new InfluenceCategory(influence, Category.valueOf(it.toUpperCase()));
            influence.updateCategory(category);
        });

        influenceProfileFileResourceQuery.findInfluenceProfileImages(parameter.getProfileImages())
            .stream()
            .forEach(it -> {
                influence.updateInfluenceProfileImage(it.toEntity());
            });

        String cId = client.create(new M2netCounselorCreateRequest(
            influence.getNickname(),
            influence.getId().toString(),
            influence.getPhoneNumber(),
            influence.getItem().getPricePerTime(),
            influence.getItem().getPrice())).getBody().getCsrid();

        influence.updateCid(cId);

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
        publisher.publishEvent(new ConnectionUpdateEvent(id, influence.getM2NetCounselorId(), status));
    }

    @Permit
    @Override
    public Item getItem(InfluenceItemParameter parameter) {
        Influence influence = influenceJpaRepository.findById(parameter.getInfluenceId())
            .orElseThrow(NotFoundInfluenceException::new);
        return influence.getItem();
    }

    @Transactional
    @Permit
    @Override
    public void changeItem(InfluenceItemParameter parameter) {
        Influence influence = influenceJpaRepository.findById(parameter.getInfluenceId())
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("find influence:{}", influence);
        influence.changeItem(parameter.getIndex());
    } // TODO: 권한을 위해 parameter로 감싸야함.

    @Override
    public SNS getSNS(Long id) {
        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug(" find influence: {}", influence);

        return influence.getSns();
    }

    @Transactional
    @Permit
    @Override
    public void update(SnsParameter parameter) {
        Influence influence = influenceJpaRepository.findById(parameter.getId())
            .orElseThrow(NotFoundInfluenceException::new);

        log.debug(" find influence: {}", influence);
        influence.updateSns(parameter.getSns());
    }
}
