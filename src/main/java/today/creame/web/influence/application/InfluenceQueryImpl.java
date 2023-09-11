package today.creame.web.influence.application;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.*;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;
import today.creame.web.common.support.Utils;
import today.creame.web.home.application.model.HomeInfluenceStatResult;
import today.creame.web.influence.application.model.*;
import today.creame.web.influence.domain.*;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.influence.exception.NotFoundItemException;
import today.creame.web.matching.applicaton.MatchingQueryService;
import today.creame.web.matching.applicaton.model.MatchingHistoryResult;
import today.creame.web.member.domain.QMember;
import today.creame.web.ranking.domain.ConsultationProduct;
import today.creame.web.ranking.domain.ConsultationProductJpaRepository;
import today.creame.web.share.support.SecurityContextSupporter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static today.creame.web.influence.domain.QHotInfluence.hotInfluence;
import static today.creame.web.influence.domain.QInfluence.influence;
import static today.creame.web.influence.domain.QInfluenceBookmark.influenceBookmark;
import static today.creame.web.influence.domain.QInfluenceCategory.influenceCategory;
import static today.creame.web.influence.domain.QInfluenceNotice.influenceNotice;
import static today.creame.web.influence.domain.QInfluenceQna.influenceQna;
import static today.creame.web.matching.domain.QMatching.matching;
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
    private final HotInfluenceJpaRepository hotInfluenceJpaRepository;
    private final MatchingQueryService matchingQueryService;
    private final JPAQueryFactory query;
    private final ConsultationProductJpaRepository consultationProductJpaRepository;

    private QInfluenceQna q = influenceQna;
    private QInfluenceBookmark b = influenceBookmark;
    private QMember m = member;
    private QInfluence i = influence;
    private QInfluenceNotice n = influenceNotice;

    private static final Map<String, Expression<?>> INFLUENCE_PROPERTY_MAP = new HashMap<>();
    static {
        INFLUENCE_PROPERTY_MAP.put("id", influence.id);
        INFLUENCE_PROPERTY_MAP.put("email", influence.email);
        INFLUENCE_PROPERTY_MAP.put("name", influence.name);
        INFLUENCE_PROPERTY_MAP.put("nickname", influence.nickname);
        INFLUENCE_PROPERTY_MAP.put("connected", influence.connected);
        INFLUENCE_PROPERTY_MAP.put("calling", influence.calling);
        INFLUENCE_PROPERTY_MAP.put("createdDateTime", influence.createdDateTime);
        INFLUENCE_PROPERTY_MAP.put("updatedDateTime", influence.updatedDateTime);
    }

    public OrderSpecifier<?> getOrderSpecifier(Pageable pageable) {
        if (!pageable.getSort().isEmpty()) {
            Sort.Order order = pageable.getSort().iterator().next();
            Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
            Expression<?> property = INFLUENCE_PROPERTY_MAP.get(order.getProperty());
            if (property != null) {
                return new OrderSpecifier(direction, property);
            }
        }
        return null;
    }

    @Override
    public HomeInfluenceStatResult queryInfluenceStat() {
        List<InfluenceCategoryGroupByDTO> results = influenceCategoryJpaRepository.groupByCount();
        log.debug("results : {}", results);
        return new HomeInfluenceStatResult(results);
    }

    @Override
    public List<InfluenceResult> listAll(Pageable pageable) {
        List<Influence> content = influenceJpaRepository.findInfluencesByBlockedAndExposed(false, true, pageable);
        return getInfluenceResults(content);
    }

    @Override
    public List<InfluenceResult> listByCategory(String category, Pageable pageable) {
        List<Influence> results = query.selectFrom(influence).join(influenceCategory).on(influence.id.eq(influenceCategory.influence.id))
                .where(influence.blocked.eq(false)
                        .and(influence.exposed.eq(true)
                        .and(influenceCategory.category.eq(Category.valueOf(category)))))
                .orderBy(getOrderSpecifier(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .distinct()
                .fetch();
        log.debug("results: {}", results);

        if(CollectionUtils.isEmpty(results)) return List.of();

        return getInfluenceResults(results);
    }

    @Override
    public InfluenceResult getInfluence(Long id) {
        Influence influence = influenceJpaRepository
            .findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("influence:{}", influence);

        InfluenceBookmark bookmark = influenceBookmarkJpaRepository.findByMemberIdAndInfluenceId(SecurityContextSupporter.orElseGetEmpty(), id).orElse(null);
        InfluenceNotice notice = influenceNoticeJpaRepository.findFirstByInfluenceIdAndDeleted(id, false).orElse(null);
        Item item = consultationProductJpaRepository.findById(influence.getItem()).map(c -> new Item(c)).orElseThrow(NotFoundItemException::new);
        return new InfluenceResult(influence, bookmark, notice, item);
    }

    public InfluenceDetailResult getDetail(Long id) {
        Influence influence = influenceJpaRepository.findById(id)
                .orElseThrow(NotFoundInfluenceException::new);

        InfluenceNotice notice = query
                .selectFrom(influenceNotice)
                .where(influenceNotice.deleted.eq(false), influenceNotice.influenceId.eq(influence.getId()))
                .fetchOne();

        List<InfluenceProfileImage> influenceProfileImages = influenceProfileImageJpaRepository.findAllByInfluenceAndDeletedOrderByOrderNumberAsc(influence, false);
        Optional<HotInfluence> hotInfluence = hotInfluenceJpaRepository.findByInfluenceId(id);
        Long hotInfluenceId = null;
        if(hotInfluence.isPresent()) {
            hotInfluenceId = hotInfluence.get().getId();
        }

        Item item = consultationProductJpaRepository
                .findById(influence.getItem()).map(c -> new Item(c))
                .orElseThrow(NotFoundItemException::new);

        return new InfluenceDetailResult(influence, notice, hotInfluenceId, influenceProfileImages, item);
    }

    // TODO: 페이징
    @Override
    public List<InfluenceResult> listByBookmarked(Long memberId, boolean called) {
//        List<Influence> results = query.selectFrom(influence).join(influenceBookmark)
//                .on(influence.id.eq(influenceBookmark.influenceId))
//                .where(influenceBookmark.memberId.eq(memberId).and(influenceBookmark.bookmarked.isTrue()))
//                .orderBy(influence.id.desc())
////                .offset(pageRequest.getOffset())
////                .limit(pageRequest.getPageSize())
//                .fetch();
//

        List<InfluenceBookmark> bookmarks = influenceBookmarkJpaRepository.findInfluenceBookmarksByMemberIdAndBookmarked(memberId, true);
        Set<Long> bookmarkedInfluenceIdSet = bookmarks.stream().map(InfluenceBookmark::getInfluenceId).collect(Collectors.toSet());
        log.debug("bookmarked influence sets: {}", bookmarkedInfluenceIdSet);

        List<Influence> results = called
                ? influenceJpaRepository.findInfluencesByIdInAndConnectedIs(bookmarkedInfluenceIdSet, true)
                : influenceJpaRepository.findInfluencesByIdIn(bookmarkedInfluenceIdSet);
        log.debug("results: {}", results);
        return this.getInfluenceResults(results);
    }

    @Override
    public List<InfluenceResult> listByMatchedRecently(Long memberId, boolean called) {
        List<MatchingHistoryResult> recentlyMatchingResults = matchingQueryService.listMatching(memberId, 1);
        Set<Long> recentlyMatchInfluenceIdSet = recentlyMatchingResults.stream().map(MatchingHistoryResult::getInfluenceId).collect(Collectors.toSet());

        List<Influence> results = called
                ? influenceJpaRepository.findInfluencesByIdInAndConnectedIs(recentlyMatchInfluenceIdSet, true)
                : influenceJpaRepository.findInfluencesByIdIn(recentlyMatchInfluenceIdSet);
        log.debug("results: {}",results);
        return getInfluenceResults(results);
    }

    @Override
    public List<InfluenceResult> listByCalling(Set<Long> callingInfluenceIds, Pageable pageable) {
        List<Influence> results = influenceJpaRepository.findInfluencesByIdIn(callingInfluenceIds);
        log.debug("results: {}", results);
        return this.getInfluenceResults(results);
    }

    @Override
    public List<InfluenceResult> listByInfluences(Set<Long> ids) {
        return this.getInfluenceResults(influenceJpaRepository.findInfluencesByIdIn(ids));
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
                .distinct()
                .fetch();
        results.stream().forEach(it -> it.processContentSecurity());
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
                .distinct()
                .fetch();
        log.debug("results: {}", results);
        return results;
    }

    @Override
    public List<InfluenceQuestionResult> getMyQuestions(Long questionerId) {
        List<InfluenceQuestionResult> results = query.select(Projections.constructor(InfluenceQuestionResult.class, influenceQna))
                .from(influenceQna)
                .join(influenceQna.questioner, member)
                .join(influenceQna.influence, influence)
                .where(influenceQna.questioner.id.eq(questionerId))
                .orderBy(influenceQna.id.desc())
                .distinct()
                .fetch();
        log.debug("results: {}", results);

        Set<Long> influenceIds = results.stream().map(result -> result.getInfluence().getId()).collect(Collectors.toSet());

        Map<Long, List<InfluenceProfileImage>> groupByProfileImages = this.groupByProfileImages(influenceIds);
        Map<Long, List<InfluenceCategory>> groupByCategories = this.groupByCategories(influenceIds);
        results.stream().forEach(it -> it.getInfluence()
                .addProfileAndCategories(
                        groupByProfileImages.getOrDefault(it.getInfluence().getId(), List.of()).stream().map(InfluenceProfileImage::getFileResourceUri).collect(Collectors.toList()),
                        groupByCategories.getOrDefault(it.getInfluence().getId(), List.of()).stream().map(InfluenceCategory::getCategory).map(Category::name).collect(Collectors.toList())
        ));


        log.debug("results: {}", results);
        return results;
    }

    @Override
    public List<InfluenceQuestionResult> getInfluenceQuestions(Long influenceId) {
        List<InfluenceQuestionResult> results = query.select(Projections.constructor(InfluenceQuestionResult.class, influenceQna))
                .from(influenceQna)
                .join(influenceQna.questioner, member)
                .join(influenceQna.influence, influence)
                .where(influenceQna.influence.id.eq(influenceId))
                .distinct()
                .fetch();
        log.debug("results: {}", results);
        return results;
    }


    private List<InfluenceResult> getInfluenceResults(List<Influence> influences) {
        Set<Long> idSet = influences.stream()
            .map(Influence::getId)
            .collect(Collectors.toSet());

        Map<Long, List<InfluenceCategory>> mapCategories = this.groupByCategories(idSet);
        Map<Long, List<InfluenceProfileImage>> mapProfileImages = this.groupByProfileImages(idSet);

        Set<Long> itemIdSet = influences.stream().map(Influence::getItem).collect(Collectors.toSet());
        Map<Long, ConsultationProduct> cpMap = consultationProductJpaRepository.findConsultationProductsByIdIn(itemIdSet).stream().collect(Collectors.toMap(ConsultationProduct::getId, Function.identity()));

        return influences.stream()
            .map(i -> {
                InfluenceResult result = new InfluenceResult(
                        i,
                        mapCategories.getOrDefault(i.getId(), List.of()),
                        mapProfileImages.getOrDefault(i.getId(), List.of()),
                        new Item(cpMap.get(i.getItem())));
                log.info("result: {}", result);
                return result;
            })
                .collect(Collectors.toList());
    }

    private Map<Long, List<InfluenceCategory>> groupByCategories(Set<Long> idSet) {
        List<InfluenceCategory> results = influenceCategoryJpaRepository.findByInfluenceIdIn(idSet);
        Map<Long, List<InfluenceCategory>> mapCategories = results
            .stream()
            .collect(groupingBy(result -> result.getInfluence().getId()));

        return mapCategories;
    }

    private Map<Long, List<InfluenceProfileImage>> groupByProfileImages(Set<Long> idSet) {
        List<InfluenceProfileImage> results = influenceProfileImageJpaRepository.findByInfluence_IdIn(idSet);
        Map<Long, List<InfluenceProfileImage>> mapProfileImages = results
            .stream()
            .collect(groupingBy(result -> result.getInfluence().getId()));

        return mapProfileImages;
    }

    @Override
    public Page<InfluenceListResult> getList(Pageable pageable, InfluenceSearchParameter parameter) {
        List<InfluenceListResult> result = query
                .select(Projections.constructor(InfluenceListResult.class,
                        influence.id,
                        influence.nickname,
                        influence.name,
                        influence.email,
                        influence.phoneNumber,
                        influence.rank,
                        new CaseBuilder()
                                .when(hotInfluence.id.isNull()).then(Boolean.FALSE).otherwise(Boolean.TRUE).as("isHotInfluence"),
                        influence.reviewCount,
                        influence.qnaCount,
                        matching.countDistinct(),
                        influence.createdDateTime,
                        influence.updatedDateTime))
                .from(influence)
                .leftJoin(hotInfluence)
                .on(influence.id.eq(hotInfluence.influenceId))
                .leftJoin(matching)
                .on(matching.influence.id.eq(influence.id))
                .where(isHotInfluence(hotInfluence, parameter.getOnlyHotInfluence()),
                        Utils.equalsOperation(influence.nickname, parameter.getNickname()),
                        Utils.equalsOperation(influence.rank, parameter.getRank()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .groupBy(influence.id)
                .distinct()
                .fetch();

        Long totalCount = query
                .select(influence.count())
                .from(influence)
                .where(isHotInfluence(hotInfluence, parameter.getOnlyHotInfluence()),
                        Utils.equalsOperation(influence.nickname, parameter.getNickname()),
                        Utils.equalsOperation(influence.rank, parameter.getRank()))
                .fetchOne();

        return new PageImpl<>(result, pageable, totalCount);
    }

    @Override
    public List<InfluenceResult> listByKeyword(Category category, String nickname, Pageable pageable) {
        QueryResults<Influence> result = query
                .selectFrom(influence)
                .distinct()
                .leftJoin(influenceCategory)
                .on(influence.id.eq(influenceCategory.influence.id))
                .where(influence.nickname.contains(nickname)
                        .or(Utils.equalsOperation(influenceCategory.category, category)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .distinct()
                .fetchResults();
        return getInfluenceResults(result.getResults());
    }

    @Override
    public Page<InfluenceQna> qnaList(InfluenceQnaSearchParameter parameter, Pageable pageable) {
        QueryResults<InfluenceQna> result = query
                .selectFrom(influenceQna)
                .where(Utils.equalsOperation(influenceQna.questioner.id, parameter.getMemberId()),
                        Utils.equalsOperation(influenceQna.questioner.nickname, parameter.getMemberNickname()),
                        Utils.equalsOperation(influenceQna.influence.id, parameter.getInfluenceId()),
                        Utils.equalsOperation(influenceQna.influence.nickname, parameter.getInfluenceNickname()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderSpecifier(pageable))
                .fetchResults();

        return new PageImpl<>(result.getResults(), pageable, result.getTotal());
    }

    public BooleanExpression isHotInfluence(SimpleExpression column, Boolean onlyHotInfluence) {
        if (Objects.isNull(onlyHotInfluence) || !onlyHotInfluence) return null;

        return column.isNotNull();
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
