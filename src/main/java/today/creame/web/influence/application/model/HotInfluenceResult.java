package today.creame.web.influence.application.model;

import com.querydsl.core.annotations.QueryProjection;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.HotInfluence;
import today.creame.web.influence.domain.Influence;

@Getter
@ToString
public class HotInfluenceResult {
    private Long id;
    private Long influenceId;
    private String extensionNumber;
    private String nickname;
    private String title;
    private String bannerImageUri;
    private List<String> categories;

    @QueryProjection
    public HotInfluenceResult(HotInfluence hotInfluence, Influence influence) {
        this.id = hotInfluence.getId();
        this.influenceId = hotInfluence.getInfluenceId();
        this.title = hotInfluence.getTitle();
        this.bannerImageUri = hotInfluence.getBannerImageUri();
        this.extensionNumber = influence.getExtensionNumber();
        this.nickname = influence.getNickname();
    }

    public void addCategory(Category category) {
        if (this.categories == null) categories = new ArrayList<>();
        categories.add(category.name());
    }
}