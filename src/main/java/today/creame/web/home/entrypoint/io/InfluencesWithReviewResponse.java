package today.creame.web.home.entrypoint.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.matching.applicaton.model.ReviewResult;
import today.creame.web.share.model.BaseFileResource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @ToString
public class InfluencesWithReviewResponse {
    private Long id;
    private String extensionNumber;
    private String nickname;
    private float rate;
    private int totalRate;
    private int rateCount;
    private int reviewCount;
    private int qnaCount;
    private Long item;
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
    private boolean calling;
    private boolean bookmarked;

    private LocalDateTime createdDateTime;

    private List<ReviewResult> reviewResults;

    public InfluencesWithReviewResponse(InfluenceResult influence, List<ReviewResult> reviews) {
        this.id = influence.getId();
        this.extensionNumber = influence.getExtensionNumber();
        this.nickname = influence.getNickname();
        this.rate = influence.getRate();
        this.totalRate = influence.getTotalRate();
        this.rateCount = influence.getRateCount();
        this.reviewCount = influence.getReviewCount();
        this.qnaCount = influence.getQnaCount();
        this.item = influence.getItem();
        this.m2NetCounselorId = influence.getM2NetCounselorId();

        this.coinPrice = influence.getCoinPrice();
        this.coinPriceTime = influence.getCoinPriceTime();
        this.coinPriceTimeUnit = influence.getCoinPriceTimeUnit();
        this.postPrice = influence.getPostPrice();
        this.postPriceTime = influence.getPostPriceTime();
        this.postPriceTimeUnit = influence.getPostPriceTimeUnit();

        this.snsCompany = influence.getSnsCompany();
        this.snsAddress = influence.getSnsAddress();
        this.categories = influence.getCategories();
        this.profileImages = influence.getProfileImages();
        this.introduction = influence.getIntroduction();
        this.greetings = influence.getGreetings();
        this.notice = influence.getNotice();
        this.connected = influence.isConnected();
        this.calling = influence.isCalling();
        this.bookmarked = influence.isBookmarked();
        this.createdDateTime = influence.getCreatedDateTime();

        this.reviewResults = reviews;
    }
}
