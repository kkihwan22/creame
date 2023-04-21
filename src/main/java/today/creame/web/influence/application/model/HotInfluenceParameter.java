package today.creame.web.influence.application.model;

import lombok.Getter;
import today.creame.web.influence.domain.HotInfluence;
import today.creame.web.share.model.BaseParameter;

@Getter
public class HotInfluenceParameter implements BaseParameter<HotInfluence> {
    private Long influenceId;
    private String title;
    private String bannerImageUri;
    private String nickname;
    private String extensionNumber;
    private String categories;
    private int orderNumber;

    public HotInfluenceParameter(Long influenceId, String title, String bannerImageUri, int orderNumber) {
        this.influenceId = influenceId;
        this.title = title;
        this.bannerImageUri = bannerImageUri;
        this.orderNumber = orderNumber;
    }

    @Override
    public HotInfluence toEntity() {
        return new HotInfluence(
                this.influenceId,
                this.title,
                this.bannerImageUri,
                this.nickname,
                this.extensionNumber,
                this.categories,
                this.orderNumber);
    }
}
