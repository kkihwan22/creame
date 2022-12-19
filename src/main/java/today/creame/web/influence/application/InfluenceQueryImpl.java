package today.creame.web.influence.application;

import static java.util.stream.Collectors.groupingBy;
import static today.creame.web.influence.domain.InfluenceStatus.OPENED;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import today.creame.web.home.application.HomeInfluenceStatQuery;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceBookmark;
import today.creame.web.influence.domain.InfluenceBookmarkJpaRepository;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceCategoryGroupByDTO;
import today.creame.web.influence.domain.InfluenceCategoryJpaRepository;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceProfileImage;
import today.creame.web.influence.domain.InfluenceProfileImageJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;

@RequiredArgsConstructor
@Component
public class InfluenceQueryImpl implements InfluenceQuery, HomeInfluenceStatQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryImpl.class);

    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceBookmarkJpaRepository influenceBookmarkJpaRepository;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;

    @Override
    public HomeInfluenceStatResult getHomeInfluenceStat() {
        List<InfluenceCategoryGroupByDTO> results = influenceCategoryJpaRepository.groupByCount();
        log.debug("results : {}", results);
        return new HomeInfluenceStatResult(results);
    }

    @Override
    public List<InfluenceResult> listAll(Pageable pageable) {
        List<Influence> content = influenceJpaRepository.findAllByStatusIn(List.of(OPENED), pageable);
        return getInfluenceResults(content);
    }

    // by category 별 p8 연애, 뷰티, 스포츠
    @Override
    public List<InfluenceResult> listByCategory(String category, Pageable pageable) {
        List<InfluenceCategory> results = influenceCategoryJpaRepository.findByCategoryIs(Category.valueOf(category), pageable);
        Set<Long> idSet = results.stream()
            .map(result -> result.getInfluence().getId())
            .collect(Collectors.toSet());

        List<Influence> influences = influenceJpaRepository.findByIdIn(idSet);
        return getInfluenceResults(influences);
    }

    @Override
    public InfluenceResult getInfluence(Long id) {
        Influence influence = influenceJpaRepository
            .findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("influence:{}", influence);

        InfluenceBookmark bookmark = influenceBookmarkJpaRepository.findById(id).orElse(null);
        return new InfluenceResult(influence, bookmark);
    }

    @Override
    public List<InfluenceResult> listByBookmarked(Long memberId, boolean called) {
        List<InfluenceBookmark> bookmarks = influenceBookmarkJpaRepository.findInfluenceBookmarksByMemberIdAndBookmarked(memberId, true);
        log.debug("bookmarks: {}", bookmarks);

        List<Influence> results = influenceJpaRepository.findByIdIn(
            bookmarks.stream().map(InfluenceBookmark::getInfluenceId).collect(Collectors.toSet())
        );

        log.debug("results: {}", results);

        if (called) {
            // todo
        }

        return this.getInfluenceResults(results);
    }

    @Override
    public List<InfluenceResult> listByInfluences(Set<Long> ids) {
        return this.getInfluenceResults(influenceJpaRepository.findByIdIn(ids));
    }

    private List<InfluenceResult> getInfluenceResults(List<Influence> influences) {
        Set<Long> idSet = influences.stream()
            .map(Influence::getId)
            .collect(Collectors.toSet());

        Map<Long, List<InfluenceCategory>> mapCategories = this.groupByCategories(idSet);
        Map<Long, List<InfluenceProfileImage>> mapProfileImages = this.groupByProfileImages(idSet);

        return influences.stream()
            .map(influence -> {
                Long key = influence.getId();
                return new InfluenceResult(influence,
                    Optional.ofNullable(mapCategories.get(key)).orElse(Collections.emptyList()),
                    Optional.ofNullable(mapProfileImages.get(key)).orElse(Collections.emptyList())
                );
            })
            .collect(Collectors.toList());
    }

    private Map<Long, List<InfluenceCategory>> groupByCategories(Set<Long> idSet) {
        List<InfluenceCategory> results = influenceCategoryJpaRepository.findByIdIn(idSet);
        log.debug("results:{}", results);

        Map<Long, List<InfluenceCategory>> mapCategories = results
            .stream()
            .collect(groupingBy(result -> result.getInfluence().getId()));

        return mapCategories;
    }

    private Map<Long, List<InfluenceProfileImage>> groupByProfileImages(Set<Long> idSet) {
        List<InfluenceProfileImage> results = influenceProfileImageJpaRepository.findByIdIn(idSet);
        log.debug("results:{}", results);

        Map<Long, List<InfluenceProfileImage>> mapProfileImages = results
            .stream()
            .collect(groupingBy(result -> result.getInfluence().getId()));

        return mapProfileImages;
    }
}
