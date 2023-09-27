package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.HotInfluenceDetailResult;
import today.creame.web.influence.application.model.HotInfluenceParameter;
import today.creame.web.influence.application.model.HotInfluenceUpdateParameter;
import today.creame.web.influence.domain.*;
import today.creame.web.influence.exception.BadRequestBannerImageException;
import today.creame.web.influence.exception.ExistHotInfluenceException;
import today.creame.web.influence.exception.NotFoundInfluenceException;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HotInfluenceServiceImpl implements HotInfluenceService {
    private final Logger log = LoggerFactory.getLogger(HotInfluenceServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;

    @Override
    public HotInfluenceDetailResult getDetail(Long id) {
        HotInfluence hotInfluence = hotInfluenceJpaRepository.findById(id)
                .orElseThrow(NotFoundInfluenceException::new);

        return new HotInfluenceDetailResult(hotInfluence);
    }

    @Transactional
    @Override
    public Long create(HotInfluenceParameter parameter) {
        Long influenceId = parameter.getInfluenceId();
        Optional<HotInfluence> optionalHotInfluence = hotInfluenceJpaRepository.findByInfluenceId(influenceId);
        if(optionalHotInfluence.isPresent()) {
            throw new ExistHotInfluenceException();
        }

        if(parameter.isEnabled() && Objects.isNull(parameter.getBannerImageUri())) {
            throw new BadRequestBannerImageException();
        }

        Influence influence = influenceJpaRepository.findById(influenceId)
                .orElseThrow(NotFoundInfluenceException::new);

        String joinedCategories = influence.getCategories().stream()
                .map(InfluenceCategory::getCategory)
                .map(Category::name)
                .collect(Collectors.joining(","));

        HotInfluence hotInfluence = new HotInfluence(
                influenceId,
                influence.getNickname(),
                influence.getExtensionNumber(),
                joinedCategories,
                parameter.getTitle(),
                parameter.isEnabled(),
                parameter.getBannerImageUri(),
                parameter.getOrderNumber());

        List<HotInfluence> originHotInfluences = hotInfluenceJpaRepository.findAllByEnabledTrue();
        if(CollectionUtils.isEmpty(originHotInfluences)) {
            hotInfluence.changeOrderNumber(1);
            hotInfluenceJpaRepository.save(hotInfluence);
        } else {
            List<HotInfluence> sortHotInfluences = sortHotInfluence(hotInfluence, originHotInfluences);
            hotInfluenceJpaRepository.saveAll(sortHotInfluences);
        }
        hotInfluenceJpaRepository.save(hotInfluence);

        return hotInfluence.getId();
    }

    @Transactional
    @Override
    public void update(HotInfluenceUpdateParameter parameter) {
        HotInfluence hotInfluence = hotInfluenceJpaRepository.findById(parameter.getId())
                .orElseThrow(NotFoundInfluenceException::new);
        if(parameter.isEnabled() && Objects.isNull(parameter.getBannerImageUri())) {
            throw new BadRequestBannerImageException();
        }
        hotInfluence.changeHotInfluence(parameter.getTitle(), parameter.getBannerImageUri(), parameter.getOrderNumber(), parameter.isEnabled());

        List<HotInfluence> originHotInfluences = hotInfluenceJpaRepository.findAllByEnabledTrue();
        if(CollectionUtils.isEmpty(originHotInfluences)) {
            hotInfluence.changeOrderNumber(1);
            hotInfluenceJpaRepository.save(hotInfluence);
        } else {
            List<HotInfluence> sortHotInfluences = sortHotInfluence(hotInfluence, originHotInfluences);
            hotInfluenceJpaRepository.saveAll(sortHotInfluences);
        }
    }

    @Transactional
    @Override
    public void enabled(Long id) {
        HotInfluence hotInfluence = hotInfluenceJpaRepository.findById(id)
                .orElseThrow(NotFoundInfluenceException::new);
        hotInfluence.enabled();
    }

    @Transactional
    @Override
    public void delete(Long influenceId) {
        HotInfluence hotInfluence = hotInfluenceJpaRepository.findByInfluenceId(influenceId)
                .orElseThrow(NotFoundInfluenceException::new);
        hotInfluenceJpaRepository.delete(hotInfluence);
    }

    @Override
    public void updateNickname(Long influenceId, String nickname) {
        Optional<HotInfluence> hotInfluence = hotInfluenceJpaRepository.findByInfluenceId(influenceId);
        if(hotInfluence.isPresent()) {
            hotInfluence.get().changeNickname(nickname);
            hotInfluenceJpaRepository.save(hotInfluence.get());
        }
    }

    @Override
    public void updateCategories(Long influenceId, List<String> categories) {
        String joinedCategories = categories.stream().collect(Collectors.joining(","));
        Optional<HotInfluence> hotInfluence = hotInfluenceJpaRepository.findByInfluenceId(influenceId);

        if(hotInfluence.isPresent()) {
            hotInfluence.get().changeCategories(joinedCategories);
            hotInfluenceJpaRepository.save(hotInfluence.get());
        }
    }

    public List<HotInfluence> sortHotInfluence(HotInfluence hotInfluence, List<HotInfluence> originHotInfluence){

        List<HotInfluence> sortHotInfluence =
                originHotInfluence.stream()
                        .filter(p -> !p.getId().equals(hotInfluence.getId()))
                        .sorted(Comparator.comparing(hotInfluence1 -> hotInfluence1.getOrderNumber()))
                        .collect(Collectors.toList());

        List<HotInfluence> saveHotInfluence = new ArrayList<>();
        if(Objects.nonNull(hotInfluence.getOrderNumber())){

            int orderNum = 1;
            boolean isFirst = true;
            for(HotInfluence hotInfluence1 : sortHotInfluence){
                if(0 >= hotInfluence.getOrderNumber() && isFirst){
                    isFirst = false;
                    orderNum++;
                }

                if(orderNum == hotInfluence.getOrderNumber()){
                    orderNum++;
                }

                hotInfluence1.changeOrderNumber(orderNum);
                saveHotInfluence.add(hotInfluence1);
                orderNum++;
            }

            if(0 >= hotInfluence.getOrderNumber()){
                hotInfluence.changeOrderNumber(1);
                saveHotInfluence.add(hotInfluence);
            }else if(hotInfluence.getOrderNumber() >= orderNum){
                hotInfluence.changeOrderNumber(orderNum);
                saveHotInfluence.add(hotInfluence);
            }else{
                saveHotInfluence.add(hotInfluence);
            }
        }

        return saveHotInfluence;
    }
}