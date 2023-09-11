package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.common.domain.FileResource;
import today.creame.web.common.domain.FileResourceJpaRepository;
import today.creame.web.common.support.Utils;
import today.creame.web.influence.application.model.*;
import today.creame.web.influence.domain.*;
import today.creame.web.influence.exception.BadRequestProfileImageSizeOverException;
import today.creame.web.influence.exception.ConflictConnectionStatusException;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.influence.exception.NotFoundItemException;
import today.creame.web.m2net.application.M2netCounselorService;
import today.creame.web.m2net.infra.feign.M2netCounselorClient;
import today.creame.web.m2net.infra.feign.io.M2netCounselorChangePriceRequest;
import today.creame.web.m2net.infra.feign.io.M2netCounselorCreateRequest;
import today.creame.web.m2net.infra.feign.io.M2netCounselorInfoUpdateRequest;
import today.creame.web.ranking.domain.ConsultationProduct;
import today.creame.web.ranking.domain.ConsultationProductJpaRepository;
import today.creame.web.share.aspect.permit.Permit;
import today.creame.web.share.domain.OnOffCondition;
import today.creame.web.share.event.ConnectionUpdateEvent;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class InfluenceServiceImpl implements InfluenceService {
    private final Logger log = LoggerFactory.getLogger(InfluenceServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final HotInfluenceService hotInfluenceService;
    private final InfluenceProfileFileResourceQuery influenceProfileFileResourceQuery;
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;
    private final FileResourceJpaRepository fileResourceJpaRepository;
    private final ApplicationEventPublisher publisher;
    private final M2netCounselorClient client;
    private final M2netCounselorService m2netCounselorService;
    private final ConsultationProductJpaRepository consultationProductJpaRepository;

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

        Item item= consultationProductJpaRepository
                .findById(influence.getItem())
                .map(c -> new Item(c))
                .orElseThrow(NotFoundItemException::new);

        String cId = client.create(new M2netCounselorCreateRequest(
            influence.getNickname(),
            influence.getId().toString(),
            influence.getPhoneNumber(),
            item.getPricePerTime(),
            item.getPrice())).getBody().getCsrid();

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
        return consultationProductJpaRepository
                .findById(influence.getItem()).map(c -> new Item(c))
                .orElseThrow(NotFoundItemException::new);
    }

    @Transactional
    @Permit
    @Override
    public void changeItem(InfluenceItemParameter parameter) {
        Influence influence = influenceJpaRepository.findById(parameter.getInfluenceId())
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("find influence:{}", influence);

        ConsultationProduct product = consultationProductJpaRepository
                .findById(parameter.getProductId())
                .orElseThrow(NotFoundItemException::new);

        influence.changeItem(product.getId());
        m2netCounselorService.changePrice(
                influence.getM2NetCounselorId(),
                new M2netCounselorChangePriceRequest(new Item(product)));
    }

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

    @Transactional
    @Override
    public void changeInfluenceInfo(InfluenceUpdateParameter parameter) {
        Optional<Influence> optional = influenceJpaRepository.findById(parameter.getInfluenceId());

        if(!optional.isPresent()) {
            return;
        }
        Influence influence = optional.get();
        influence.updateNickname(parameter.getNickname());
        influence.updatePhoneNumber(parameter.getPhoneNumber());

        influenceJpaRepository.save(influence);
        m2netCounselorService.updateInfluenceInfo(influence.getM2NetCounselorId(), new M2netCounselorInfoUpdateRequest(influence));
        hotInfluenceService.updateNickname(influence.getId(), influence.getNickname());
    }

    @Transactional
    @Override
    public void updateBlocked(Long id) {
        Influence influence = influenceJpaRepository.findById(id)
                .orElseThrow(NotFoundInfluenceException::new);

        influence.blocked();
        influenceJpaRepository.save(influence);
    }

    @Transactional
    @Override
    public void updateProfileImages(InfluenceProfileImageUpdateParameter parameter) {
        Influence influence = influenceJpaRepository.findById(parameter.getInfluenceId())
                .orElseThrow(NotFoundInfluenceException::new);

        //delete
        int deleteImageSize = 0;
        if(CollectionUtils.isNotEmpty(parameter.getDeleteFileResourceFileIds())) {
            List<InfluenceProfileImage> deleteInfluenceProfileImages = influenceProfileImageJpaRepository.findAllByFileResourceIdInAndDeletedFalse(parameter.getDeleteFileResourceFileIds());
            deleteInfluenceProfileImages.stream().forEach(v -> v.deleted());
            influenceProfileImageJpaRepository.saveAll(deleteInfluenceProfileImages);
            log.info("deleteInfluenceProfileImages.size() {}", deleteInfluenceProfileImages.size());
            deleteImageSize = deleteInfluenceProfileImages.size();

            List<FileResource> deleteFileResource = fileResourceJpaRepository.findAllByIdInAndDeletedFalse(parameter.getDeleteFileResourceFileIds());
            deleteFileResource.stream().forEach(v -> v.deleted());
            fileResourceJpaRepository.saveAll(deleteFileResource);
        }

        //insert
        if(CollectionUtils.isNotEmpty(parameter.getCreateFileResourceFileIds())) {
            List<InfluenceProfileImage> originInfluenceProfileImages = influenceProfileImageJpaRepository.findAllByInfluenceAndDeletedOrderByOrderNumberAsc(influence, false);
            List<FileResource> createFileResource = fileResourceJpaRepository.findAllByIdInAndDeletedFalse(parameter.getCreateFileResourceFileIds());

            int totalImageSize = originInfluenceProfileImages.size() - deleteImageSize + createFileResource.size();
            if(totalImageSize > 4) {
                throw new BadRequestProfileImageSizeOverException();
            }

            int orderNum = 0;
            for(InfluenceProfileImage influenceProfileImage : originInfluenceProfileImages) {
                influenceProfileImage.changeOrderNumber(orderNum);
                orderNum++;
            }

            List<InfluenceProfileImage> saveInfluenceProfileImages = new ArrayList<>();
            for(FileResource fileResource : createFileResource) {
                String fileImageUri = Utils.combineFileResourceUrl(fileResource);
                InfluenceProfileImage influenceProfileImage =  new InfluenceProfileImage(fileResource.getId(), fileImageUri, false, orderNum);
                influenceProfileImage.changeInfluence(influence);
                saveInfluenceProfileImages.add(influenceProfileImage);
                orderNum++;
            }
            log.info("saveInfluenceProfileImages.size() {}", saveInfluenceProfileImages.size());
            influenceProfileImageJpaRepository.saveAll(saveInfluenceProfileImages);
        }
    }

    @Transactional
    @Override
    public void changedExposeStatus(Boolean status) {
        Long id = SecurityContextSupporter.getId();
        Influence influence = influenceJpaRepository.findById(id).orElseThrow(NotFoundInfluenceException::new);
        influence.changedExposeStatus(status);
    }

    @Transactional
    @Override
    public void updateConnectionStatusByAdmin(Long id, OnOffCondition status) {
        Influence influence = influenceJpaRepository.findById(id)
                .orElseThrow(NotFoundInfluenceException::new);

        if (status == OnOffCondition.ON && influence.isConnected()) {
            throw new ConflictConnectionStatusException();
        }

        if (status == OnOffCondition.OFF && !influence.isConnected()) {
            throw new ConflictConnectionStatusException();
        }

        influence.updateConnect();

        if(status == OnOffCondition.ON) {
            m2netCounselorService.on(influence.getM2NetCounselorId());
        }else {
            m2netCounselorService.off(influence.getM2NetCounselorId());
        }
    }

    @Transactional
    @Override
    public void updateInfluenceInfo(InfluenceUpdateInfoParameter parameter) {
        List<InfluenceCategory> categories = influenceCategoryJpaRepository.findByInfluenceIdIn(Set.of(parameter.getId()));
        influenceCategoryJpaRepository.deleteAll(categories);

        Influence influence = influenceJpaRepository.findById(parameter.getId())
                .orElseThrow(NotFoundInfluenceException::new);

        influence.updateInfo(parameter.getName(), parameter.getRank(), parameter.getIntroduction());
        parameter.getCategories().stream().forEach(it -> {
            InfluenceCategory category = new InfluenceCategory(influence, Category.valueOf(it.toUpperCase()));
            influence.updateCategory(category);
        });

        influenceJpaRepository.save(influence);

        hotInfluenceService.updateCategories(influence.getId(), parameter.getCategories());
    }
}
