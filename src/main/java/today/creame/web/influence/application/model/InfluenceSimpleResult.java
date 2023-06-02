package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Influence;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class InfluenceSimpleResult {
    private Long id;
    private String influenceNickname;
    private List<String> profileImages;
    private List<String> categories;

    public InfluenceSimpleResult(Influence influence, List<String> profileImages, List<String> categories) {
        this.id = influence.getId();
        this.influenceNickname = influence.getNickname();
        this.profileImages = profileImages;
        this.categories = categories;
    }

    public InfluenceSimpleResult(Influence influence) {
        this(influence, Collections.emptyList(), Collections.emptyList());
    }

    public void addProfileAndCategories(List<String> profileImages, List<String> categories) {
        this.profileImages = profileImages;
        this.categories = categories;
    }
}
