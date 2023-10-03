package today.creame.web.influence.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.HotInfluence;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceCategory;

import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter @ToString
public class HotInfluenceParameter {
    private Long influenceId;
    private String bannerName;
    private String title;
    private boolean enabled;
    private String bannerImageUri;
    private int orderNumber;

    public HotInfluence toEntity(Influence influence) {
        return new HotInfluence(
                influence.getId(),
                influence.getNickname(),
                influence.getExtensionNumber(),
                influence.getCategories().stream()
                        .map(InfluenceCategory::getCategory)
                        .map(Category::name)
                        .collect(Collectors.joining(",")),
                this.bannerName,
                this.title,
                this.enabled,
                this.bannerImageUri,
                this.orderNumber
        );
    }
}
