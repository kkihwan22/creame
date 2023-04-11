package today.creame.web.influence.application.model;

import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import today.creame.web.common.domain.FileResource;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.share.model.BaseFileResource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static today.creame.web.common.support.Utils.combineFileResourceUrl;

@Getter
public class InfluenceApplicationDetailResult {
    private Long id;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;
    private String introduction;
    private List<String> categories;
    private List<BaseFileResource> profileImages;
    private String status;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;

    public InfluenceApplicationDetailResult(InfluenceApplication result, List<FileResource> fileResources) {
        this.id = result.getId();
        this.name = result.getName();
        this.nickname = result.getNickname();
        this.phoneNumber = result.getPhoneNumber();
        this.email = result.getEmail();
        this.introduction = result.getIntroduction();
        this.categories = Arrays.stream(result.getCategories().split(",")).collect(Collectors.toList());
        this.profileImages = CollectionUtils.emptyIfNull(fileResources)
                .stream()
                .map(file -> new BaseFileResource(file.getId(), combineFileResourceUrl(file)))
                .collect(Collectors.toList());
        this.status = result.getStatus().name();
        this.createdDateTime = result.getCreatedDateTime();
        this.updatedDateTime = result.getUpdatedDateTime();
    }
}
