package today.creame.web.influence.application.model;

import com.querydsl.core.annotations.QueryProjection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
    private String bannerName;
    private String title;
    private String bannerImageUri;
    private List<String> categories;

    public HotInfluenceResult(HotInfluence hotInfluence) {
        this.id = hotInfluence.getId();
        this.influenceId = hotInfluence.getInfluenceId();
        this.extensionNumber = hotInfluence.getExtensionNumber();
        this.nickname = hotInfluence.getNickname();
        this.bannerName = hotInfluence.getBannerName();
        this.title = hotInfluence.getTitle();
        this.bannerImageUri = hotInfluence.getBannerImageUri();
        this.categories = Arrays.stream(hotInfluence.getCategories().split(",")).collect(Collectors.toList());
    }
}