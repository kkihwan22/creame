package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Influence;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@ToString
public class InfluenceSimpleResult {
    private Long id;
    private String nickname;
    private List<String> profileImages;
    private List<String> categories;
    private LocalDateTime createdDateTime;

    public InfluenceSimpleResult(Influence influence, List<String> profileImages, List<String> categories) {
        this.id = influence.getId();
        this.nickname = influence.getNickname();
        this.createdDateTime = influence.getCreatedDateTime();
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
