package today.creame.web.influence.application.model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.Greetings;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceBookmark;
import today.creame.web.influence.domain.InfluenceCategory;
import today.creame.web.influence.domain.InfluenceProfileImage;

@Getter @ToString
public class InfluenceResult {
    private Long id;
    private String extensionNumber;
    private String nickname;
    private float rate;
    private int rateCount;
    private int reviewCount;
    private int qnaCount;
    private int item;
    private String snsCompany;
    private String snsAddress;

    private List<String> categories;
    private List<String> profileImages;

    private String introduction;
    private String greetings;
    private String notice;

    private boolean connected;
    private boolean calling = false;
    private boolean bookmarked = false;

    public InfluenceResult(
            Influence influence,
            InfluenceBookmark bookmark,
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
        this.introduction = influence.getIntroduction();
        this.greetings = Optional.ofNullable(influence.getGreetings())
                .map(Greetings::getFileResourceUri)
                .orElse(null);
        this.notice = influence.getNotice();
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
        this(influence, null, categories, profileImages);
    }

    public InfluenceResult(Influence influence, InfluenceBookmark bookmark) {
        this(influence, bookmark, influence.getCategories(), influence.getProfileImages());
    }
}