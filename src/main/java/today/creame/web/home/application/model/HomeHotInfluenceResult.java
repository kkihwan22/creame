package today.creame.web.home.application.model;

import java.util.List;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.HotInfluence;

@Getter @ToString
public class HomeHotInfluenceResult {

    private Long id;
    private String extensionNumber;
    private String title;
    private String nickname;
    private String bannerImageUri;
    private List<String> categories;

    public HomeHotInfluenceResult(HotInfluence influence) {

        this.id = influence.getId();
        this.title = influence.getTitle();
        this.bannerImageUri = influence.getBannerImageUri();
    }

}
