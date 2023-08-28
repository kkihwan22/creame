package today.creame.web.influence.application.model;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import today.creame.web.influence.domain.*;
import today.creame.web.share.model.BaseFileResource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Getter
public class InfluenceDetailResult {
    private Long id;
    private Long hotInfluenceId;
    private String extensionNumber;
    private String nickname;
    private String name;
    private String email;
    private String phoneNumber;
    private String rank;
    private boolean blocked;
    private float rate;
    private int totalRate;
    private int rateCount;
    private int reviewCount;
    private int qnaCount;
    private int item;
    private String m2NetCounselorId;
    private int coinPrice;
    private int coinPriceTime;
    private String coinPriceTimeUnit;
    private int postPrice;
    private int postPriceTime;
    private String postPriceTimeUnit;
    private String snsCompany;
    private String snsAddress;
    private List<String> categories;
    private List<ProfileImage> profileImages;
    private String introduction;
    private String greetings;
    private String notice;
    private List<BaseFileResource> noticeAttachFiles = new ArrayList<>();
    private boolean connected;
    private boolean calling = false;
    private boolean bookmarked = false;
    private int bookmarkCount;
    private int reviewAnswerCount;
    private int reviewNotAnswerCount;
    private int qnaAnswerCount;
    private int qnaNotAnswerCount;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public InfluenceDetailResult(
            Influence influence,
            InfluenceNotice influenceNotice,
            Long hotInfluenceId,
            List<InfluenceProfileImage> influenceProfileImages) {
        this.id = influence.getId();
        this.hotInfluenceId = hotInfluenceId;
        this.extensionNumber = influence.getExtensionNumber();
        this.nickname = influence.getNickname();
        this.name = influence.getName();
        this.email = influence.getEmail();
        this.phoneNumber = influence.getPhoneNumber();
        this.rank = influence.getRank().name();
        this.blocked = influence.isBlocked();
        this.totalRate = influence.getRate();
        this.rateCount = influence.getRateCount();
        this.rate = influence.avgRate();
        this.reviewCount = influence.getReviewCount();
        this.qnaCount = influence.getQnaCount();
        this.item = influence.getItem().getIndex();
        this.coinPrice = influence.getItem().getPrice();
        this.coinPriceTime = influence.getItem().getPricePerTime();
        this.coinPriceTimeUnit = influence.getItem().getTimeUnit().name();
        this.postPrice = influence.getItem().getPrice() + 400;
        this.postPriceTime = influence.getItem().getPricePerTime();
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

        this.categories = CollectionUtils.emptyIfNull(influence.getCategories()).stream()
                .map(InfluenceCategory::getCategory)
                .map(Category::name)
                .collect(Collectors.toList());

        this.profileImages = CollectionUtils.emptyIfNull(influenceProfileImages).stream()
                .map(ProfileImage::of)
                .collect(Collectors.toList());

        if (influence.getSns() != null) {
            this.snsCompany = influence.getSns().getCompany().name();
            this.snsAddress = influence.getSns().getAddress();
        }

        this.bookmarkCount = influence.getBookmarkCount();
        this.reviewAnswerCount = influence.getReviewAnswerCount();
        this.reviewNotAnswerCount = influence.getReviewNotAnswerCount();
        this.qnaAnswerCount = influence.getQnaAnswerCount();
        this.qnaNotAnswerCount = influence.getQnaNotAnswerCount();
        this.createdDt = influence.getCreatedDateTime();
        this.updatedDt = influence.getUpdatedDateTime();
    }

    @Getter
    public static class ProfileImage {
        private Long id;
        private Long fileResourceId;
        private String uri;

        public ProfileImage(Long id, Long fileResourceId, String uri) {
            this.id = id;
            this.fileResourceId = fileResourceId;
            this.uri = uri;
        }

        public static ProfileImage of(InfluenceProfileImage influenceProfileImage) {
            return new ProfileImage(influenceProfileImage.getId(), influenceProfileImage.getFileResourceId(), influenceProfileImage.getFileResourceUri());
        }

        public static List<ProfileImage> of(List<InfluenceProfileImage> influenceProfileImage) {

            return influenceProfileImage.stream().map(ProfileImage::of).collect(Collectors.toList());
        }
    }
}
