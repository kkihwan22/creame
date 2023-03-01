package today.creame.web.influence.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.Greetings;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceBookmark;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceNotice;
import today.creame.web.influence.domain.InfluenceProfileImage;
import today.creame.web.share.model.BaseFileResource;

@Getter
@ToString
public class InfluenceResult {
    private Long id;
    private String extensionNumber;
    private String nickname;
    private float rate;
    private int rateCount;
    private int reviewCount;
    private int qnaCount;
    private int item;
    private String m2NetCounselorId;

    // TODO: 제거하기
    private int coinPrice;
    private int coinPriceTime;
    private String coinPriceTimeUnit;
    private int postPrice;
    private int postPriceTime;
    private String postPriceTimeUnit;

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

    public InfluenceResult(
        Influence influence,
        InfluenceBookmark bookmark,
        InfluenceNotice influenceNotice,
        List<InfluenceCategory> categories,
        List<InfluenceProfileImage> profileImages) {
        this.id = influence.getId();
        this.extensionNumber = influence.getExtensionNumber();
        this.nickname = influence.getNickname();
        this.rate = influence.getRate();
        this.rateCount = influence.getRateCount();
        this.reviewCount = influence.getReviewCount();
        this.qnaCount = influence.getQnaCount();
        this.item = influence.getItem().getIndex();

        this.coinPrice = influence.getItem().getPrice();
        this.coinPriceTime = influence.getItem().getPricePerTime();
        this.coinPriceTimeUnit = influence.getItem().getTimeUnit().name();

        this.postPrice = 900;
        this.postPriceTime = 30;
        this.postPriceTimeUnit = TimeUnit.SECONDS.name();

        this.m2NetCounselorId = influence.getM2NetCounselorId();
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
    }

    public InfluenceResult(Influence influence, List<InfluenceCategory> categories, List<InfluenceProfileImage> profileImages) {
        this(influence, null, null, categories, profileImages);
    }

    public InfluenceResult(Influence influence, InfluenceBookmark bookmark, InfluenceNotice notice) {
        this(influence, bookmark, notice, influence.getCategories(), influence.getProfileImages());
    }
}