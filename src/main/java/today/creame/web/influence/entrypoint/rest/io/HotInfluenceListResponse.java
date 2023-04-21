package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.domain.HotInfluence;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class HotInfluenceListResponse {
    private Long id;
    private Long influenceId;
    private String title;
    private String bannerImageUri;
    private String nickName;
    private String extensionNumber;
    private List<String> categories;
    private int orderNumber;
    private boolean enabled;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public HotInfluenceListResponse(HotInfluence hotInfluence) {
        this.id = hotInfluence.getId();
        this.influenceId = hotInfluence.getInfluenceId();
        this.title = hotInfluence.getTitle();
        this.bannerImageUri = hotInfluence.getBannerImageUri();
        this.nickName = hotInfluence.getNickname();
        this.extensionNumber = hotInfluence.getExtensionNumber();
        this.orderNumber = hotInfluence.getOrderNumber();
        this.enabled = hotInfluence.isEnabled();
        this.createdDt = hotInfluence.getCreatedDateTime();
        this.updatedDt = hotInfluence.getUpdatedDateTime();
        this.categories = Arrays.stream(hotInfluence.getCategories().split(",")).collect(Collectors.toList());
    }
}
