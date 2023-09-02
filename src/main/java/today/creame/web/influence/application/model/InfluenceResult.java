package today.creame.web.influence.application.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.*;
import today.creame.web.share.model.BaseFileResource;

@Getter
@ToString
public class InfluenceResult {
    private Long id;
    private String extensionNumber;
    private String nickname;
    private float rate;
    private int totalRate;
    private int rateCount;
    private int reviewCount;
    private int reviewAnswerCount;
    private int qnaCount;
    private int answerCount;
    private Long item;
    private String m2NetCounselorId;

    // TODO: 제거하기
    private int coinPrice;
    private int coinPriceTime;
    private String coinPriceTimeUnit;
    private int postPrice;
    private int postPriceTime;
    private String postPriceTimeUnit;

    private Rank rank;
    private String snsCompany;
    private String snsAddress;

    private List<String> categories;
    private List<String> profileImages;

    private String introduction;
    private String greetings;
    private String notice;
    private List<BaseFileResource> noticeAttachFiles = new ArrayList<>();

    private boolean connected;
    private boolean calling = false;
    private boolean bookmarked = false;

    private LocalDateTime createdDateTime;

    public InfluenceResult(
        Influence influence,
        InfluenceBookmark bookmark,
        InfluenceNotice influenceNotice,
        List<InfluenceCategory> categories,
        List<InfluenceProfileImage> profileImages,
        Item item
    ) {
        this.id = influence.getId();
        this.extensionNumber = influence.getExtensionNumber();
        this.nickname = influence.getNickname();
        this.totalRate = influence.getRate();
        this.rateCount = influence.getRateCount();
        this.rate = influence.avgRate();
        this.reviewCount = influence.getReviewCount();
        this.reviewAnswerCount = influence.getReviewAnswerCount();
        this.qnaCount = influence.getQnaCount();
        this.answerCount = influence.getQnaAnswerCount();
        this.item = Optional.ofNullable(item).map(Item::getId).orElse(null);

        this.coinPrice = item.getPrice();
        this.coinPriceTime = item.getPricePerTime();
        this.coinPriceTimeUnit = item.getTimeUnit().name();

        this.postPrice = item.getPrice() + 400;
        this.postPriceTime = item.getPricePerTime();
        this.postPriceTimeUnit = TimeUnit.SECONDS.name();

        this.m2NetCounselorId = influence.getM2NetCounselorId();
        this.rank = influence.getRank();
        this.introduction = influence.getIntroduction();
        this.greetings = Optional.ofNullable(influence.getGreetings())
            .map(Greetings::getFileResourceUri)
            .orElse(null);

        if (influenceNotice != null) {
            this.notice = influenceNotice.getContent();
            this.noticeAttachFiles = influenceNotice.getAttachFiles()
                .stream()
                .map(file -> new BaseFileResource(file.getFileId(), file.getFileUri()))
                .collect(Collectors.toList());
        }

        this.connected = influence.isConnected();
        this.calling = influence.isCalling();

        this.categories = categories.stream()
            .map(InfluenceCategory::getCategory)
            .map(Category::name)
            .collect(Collectors.toList());

        this.profileImages = profileImages.stream()
            .map(InfluenceProfileImage::getFileResourceUri)
                .collect(Collectors.toList());

        if (influence.getSns() != null) {
            this.snsCompany = influence.getSns().getCompany().name();
            this.snsAddress = influence.getSns().getAddress();
        }

        if (bookmark != null) {
            this.bookmarked = bookmark.isBookmarked();
        }

        this.createdDateTime = influence.getCreatedDateTime();
    }

    public InfluenceResult(Influence influence, List<InfluenceCategory> categories, List<InfluenceProfileImage> profileImages) {
        this(influence, null, null, categories, profileImages, null);
    }

    public InfluenceResult(Influence influence, InfluenceBookmark bookmark, InfluenceNotice notice, Item item) {
        this(influence, bookmark, notice, influence.getCategories(), influence.getProfileImages(), item);
    }
}