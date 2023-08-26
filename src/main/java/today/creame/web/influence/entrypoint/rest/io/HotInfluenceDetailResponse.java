package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.application.model.HotInfluenceDetailResult;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class HotInfluenceDetailResponse {
    private Long id;
    private Long influenceId;
    private String title;
    private String bannerImageUri;
    private String nickname;
    private String extensionNumber;
    private List<String> categories;
    private int orderNumber;
    private boolean enabled;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public HotInfluenceDetailResponse(HotInfluenceDetailResult result) {
        this.id = result.getId();
        this.influenceId = result.getInfluenceId();
        this.title = result.getTitle();
        this.bannerImageUri = result.getBannerImageUri();
        this.nickname = result.getNickname();
        this.extensionNumber = result.getExtensionNumber();
        this.orderNumber = result.getOrderNumber();
        this.enabled = result.isEnabled();
        this.createdDt = result.getCreatedDt();
        this.updatedDt = result.getUpdatedDt();
        this.categories = result.getCategories();
    }
}
