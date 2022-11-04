package today.creame.web.home.application.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.curation.domain.HotInfluence;

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
        this.extensionNumber = influence.getExtensionNumber();
        this.title = influence.getTitle();
        this.nickname = influence.getNickname();
        this.bannerImageUri = influence.getBannerImageUri();
        this.categories = Arrays.stream(influence.getCategories().split(",")).collect(Collectors.toList());
    }

}
