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
import today.creame.web.influence.exception.*;

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

        HotInfluence hotInfluence = parameter.toEntity(influence);
        List<HotInfluence> results = hotInfluenceJpaRepository.findAllByEnabled(Boolean.TRUE);
        
        if (results.isEmpty()) {
            hotInfluence.changeOrderNumber(1);
            hotInfluenceJpaRepository.save(hotInfluence);
            return hotInfluence.getId();
        }

        results.add(hotInfluence);
        this.sortHotInfluences(results);
        return hotInfluence.getId();
    }

    @Transactional
    @Override
    public void update(HotInfluenceUpdateParameter parameter) {
        // TODO: 검증로직 위치 변경
        if(parameter.isEnabled() && Objects.isNull(parameter.getBannerImageUri())) {
            throw new BadRequestBannerImageException();
        }

        List<HotInfluence> results = hotInfluenceJpaRepository.findAll();
        if (results.isEmpty()) {
            throw new BadRequestException();
        }

        HotInfluence matchedResult = results.stream()
                .filter(result -> result.getId().equals(parameter.getId())).findFirst().orElseThrow(NotFoundHotInfluencerException::new);

        matchedResult.changeHotInfluence(
                parameter.getBannerName(),
                parameter.getTitle(),
                parameter.getBannerImageUri(),
                parameter.getOrderNumber(),
                parameter.isEnabled());

        if (!matchedResult.isEnabled()) return;

        List<HotInfluence> filteredResults = results.stream().filter(HotInfluence::isEnabled).collect(Collectors.toList());
        this.sortHotInfluences(filteredResults);
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

    // TODO: 위치변경 - 도대체 왜..
    @Transactional
    @Override
    public void updateNickname(Long influenceId, String nickname) {
        Optional<HotInfluence> hotInfluence = hotInfluenceJpaRepository.findByInfluenceId(influenceId);
        if(hotInfluence.isPresent()) {
            hotInfluence.get().changeNickname(nickname);
            hotInfluenceJpaRepository.save(hotInfluence.get());
        }
    }

    // TODO: 위치변경 - 도대체 왜..
    @Transactional
    @Override
    public void updateCategories(Long influenceId, List<String> categories) {
        String joinedCategories = categories.stream().collect(Collectors.joining(","));
        Optional<HotInfluence> hotInfluence = hotInfluenceJpaRepository.findByInfluenceId(influenceId);

        if(hotInfluence.isPresent()) {
            hotInfluence.get().changeCategories(joinedCategories);
            hotInfluenceJpaRepository.save(hotInfluence.get());
        }
    }

    private void sortHotInfluences(List<HotInfluence> hotInfluences) {
        List<HotInfluence> sortedResults = hotInfluences.stream()
                .sorted(Comparator.comparing(HotInfluence::getOrderNumber))
                .collect(Collectors.toList());

        for (int i = 0; i < sortedResults.size(); i++) {
            HotInfluence result = sortedResults.get(i);
            result.changeOrderNumber(i+1);
        }

        hotInfluenceJpaRepository.saveAll(sortedResults);
    }
}