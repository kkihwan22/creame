package today.creame.web.influence.application;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.InfluenceDetailResult;
import today.creame.web.influence.application.model.InfluenceQuestionResult;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.influence.domain.*;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.member.domain.QMember;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static today.creame.web.influence.domain.QInfluence.influence;
import static today.creame.web.influence.domain.QInfluenceBookmark.influenceBookmark;
import static today.creame.web.influence.domain.QInfluenceNotice.influenceNotice;
import static today.creame.web.influence.domain.QInfluenceQna.influenceQna;
import static today.creame.web.member.domain.QMember.member;

@RequiredArgsConstructor
@Component
public class InfluenceQueryImpl implements InfluenceQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQueryImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceNoticeJpaRepository influenceNoticeJpaRepository;
    private final InfluenceBookmarkJpaRepository influenceBookmarkJpaRepository;
    private final InfluenceCategoryJpaRepository influenceCategoryJpaRepository;
    private final InfluenceProfileImageJpaRepository influenceProfileImageJpaRepository;
    private final JPAQueryFactory query;

    private QInfluenceQna q = influenceQna;
    private QInfluenceBookmark b = influenceBookmark;
    private QMember m = member;
    private QInfluence i = influence;
    private QInfluenceNotice n = influenceNotice;

    @Override
    public HomeInfluenceStatResult queryInfluenceStat() {
        List<InfluenceCategoryGroupByDTO> results = influenceCategoryJpaRepository.groupByCount();
        log.debug("results : {}", results);
        return new HomeInfluenceStatResult(results);
    }

    @Override
    public List<InfluenceResult> listAll(Pageable pageable) {
        List<Influence> content = influenceJpaRepository.findAllByBlocked(Boolean.FALSE, pageable);
        return getInfluenceResults(content);
    }

    // TODO: 개선 필요!
    // by category 별 p8 연애, 뷰티, 스포츠
    @Override
    public List<InfluenceResult> listByCategory(String category, Pageable pageable) {
        List<InfluenceCategory> results = influenceCategoryJpaRepository.findByCategoryIs(Category.valueOf(category), pageable);
        Set<Long> idSet = results.stream()
            .map(result -> result.getId())
            .collect(Collectors.toSet());

        List<Influence> influences = influenceJpaRepository.findByIdIn(idSet);
        return getInfluenceResults(influences);
    }

    @Override
    public InfluenceResult getInfluence(Long id) {

//        Influence influence = query
//            .selectFrom(i)
//            .leftJoin()
//            .fetchOne();

        // TODO: QueryDsl 로 변경
        Influence influence = influenceJpaRepository
            .findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("influence:{}", influence);

        InfluenceBookmark bookmark = influenceBookmarkJpaRepository.findById(id).orElse(null);
        InfluenceNotice notice = influenceNoticeJpaRepository.findFirstByInfluenceIdAndDeleted(id, false).orElse(null);
        return new InfluenceResult(influence, bookmark, notice);
    }

    public InfluenceDetailResult getDetail(Long id) {
        Influence influence = influenceJpaRepository.findById(id)
                .orElseThrow(NotFoundInfluenceException::new);

        InfluenceNotice notice = query
                .selectFrom(influenceNotice)
                .where(influenceNotice.deleted.eq(false), influenceNotice.influenceId.eq(influence.getId()))
                .fetchOne();

        return new InfluenceDetailResult(influence, notice);
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

    @Override
    public List<InfluenceQuestionResult> getQuestionsByInfluence(Long influenceId, PageRequest pageRequest) {
        log.debug("PageRequest: {}", pageRequest);
        List<InfluenceQuestionResult> results = query.select(Projections.constructor(InfluenceQuestionResult.class, influenceQna))
                .from(influenceQna)
                .join(influenceQna.questioner, member)
                .join(influenceQna.influence, influence)
                .where(influenceQna.influence.id.eq(influenceId))
                .orderBy(influenceQna.id.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
        log.debug("results: {}", results);
        return results;
    }

    @Override
    public List<InfluenceQuestionResult> getInfluenceQuestionsByMe(Long influenceId, Long questionerId, PageRequest pageRequest) {
        log.debug("PageRequest: {}", pageRequest);
        List<InfluenceQuestionResult> results = query.select(Projections.constructor(InfluenceQuestionResult.class, influenceQna))
                .from(influenceQna)
                .join(influenceQna.questioner, member)
                .join(influenceQna.influence, influence)
                .where(influenceQna.influence.id.eq(influenceId).and(influenceQna.questioner.id.eq(questionerId)))
                .orderBy(influenceQna.id.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetch();
        log.debug("results: {}", results);
        return null;
    }

    @Override
    public List<InfluenceQuestionResult> getMyQuestions(Long questionerId) {
        List<InfluenceQuestionResult> results = query.select(Projections.constructor(InfluenceQuestionResult.class, influenceQna))
                .from(influenceQna)
                .join(influenceQna.questioner, member)
                .join(influenceQna.influence, influence)
                .where(influenceQna.questioner.id.eq(questionerId))
                .orderBy(influenceQna.id.desc())
                .fetch();
        log.debug("results: {}", results);

        Set<Long> influenceIds = results.stream().map(result -> result.getInfluence().getId()).collect(Collectors.toSet());
        List<InfluenceProfileImage> profileImages = influenceProfileImageJpaRepository.findByInfluence_IdIn(influenceIds);
        List<InfluenceCategory> categories = influenceCategoryJpaRepository.findByInfluenceIdIn(influenceIds);

        results.stream().forEach(it -> it.getInfluence().addProfileAndCategories(
                profileImages.stream().map(profile -> profile.getFileResourceUri()).collect(Collectors.toList()),
                categories.stream().map(category -> category.getCategory().name()).collect(Collectors.toList())));

        log.debug("results: {}", results);
        return results;
    }

    @Override
    public List<InfluenceQuestionResult> getInfluenceQuestions(Long influenceId) {
        return null;
    }


    private List<InfluenceResult> getInfluenceResults(List<Influence> influences) {
        Set<Long> idSet = influences.stream()
            .map(Influence::getId)
            .collect(Collectors.toSet());

        Map<Long, List<InfluenceCategory>> mapCategories = this.groupByCategories(idSet);
        Map<Long, List<InfluenceProfileImage>> mapProfileImages = this.groupByProfileImages(idSet);

        log.debug("map categories: {}", mapCategories);
        log.debug("map profileImages: {}", mapProfileImages);

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
        List<InfluenceCategory> results = influenceCategoryJpaRepository.findByInfluenceIdIn(idSet);
        log.debug("results:{}", results);

        Map<Long, List<InfluenceCategory>> mapCategories = results
            .stream()
            .collect(groupingBy(result -> result.getInfluence().getId()));

        return mapCategories;
    }

    private Map<Long, List<InfluenceProfileImage>> groupByProfileImages(Set<Long> idSet) {
        List<InfluenceProfileImage> results = influenceProfileImageJpaRepository.findByInfluence_IdIn(idSet);
        log.debug("results:{}", results);

        Map<Long, List<InfluenceProfileImage>> mapProfileImages = results
            .stream()
            .collect(groupingBy(result -> result.getInfluence().getId()));

        return mapProfileImages;
    }



    //    private BooleanBuilder buildWhere(InfluenceQnaQueryParameter parameter) {
//        BooleanBuilder where = new BooleanBuilder();
//        if (parameter.requesterId != null)
//            where.and(influenceQna.questioner.id.eq(parameter.requesterId));
//
//        if (parameter.influenceId != null) {
//            where.and(influenceQna.influence.id.eq(parameter.influenceId));
//        }
//
//        if (parameter.answered != null) {
//            if (parameter.answered) {
//                where.and(influenceQna.answers.isNotNull());
//            } else {
//                where.and(influenceQna.answers.isNull());
//            }
//        }
//
//        return where;
//    }
}
