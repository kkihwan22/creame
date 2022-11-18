package today.creame.web.influence.application.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.HotInfluence;

@Getter
@ToString
public class HotInfluenceResult {

    private Long id;
    private String extensionNumber;
    private String nickname;
    private String title;
    private String bannerImageUri;
    private List<String> categories;

    public HotInfluenceResult(HotInfluence hotInfluence) {
        this.id = hotInfluence.getId();
        this.extensionNumber = hotInfluence.getExtensionNumber();
        this.nickname = hotInfluence.getNickname();
        this.title = hotInfluence.getTitle();
        this.bannerImageUri = hotInfluence.getBannerImageUri();
        this.categories = Arrays.stream(hotInfluence.getCategories().split(",")).collect(Collectors.toList());
    }
}
