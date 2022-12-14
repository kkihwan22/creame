package today.creame.web.influence.application.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.InfluenceApplication;

@Getter @ToString
public class InfluenceApplicationResult {

    private Long id;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;
    private String introduction;
    private List<String> categories;
    private List<Long> profileImages;
    private String status;

    public InfluenceApplicationResult(InfluenceApplication result) {

        this.id = result.getId();
        this.name = result.getName();
        this.nickname = result.getNickname();
        this.phoneNumber = result.getPhoneNumber();
        this.email = result.getEmail();
        this.introduction = result.getIntroduction();

        this.categories = Arrays.stream(result.getCategories().split(",")).collect(Collectors.toList());
        this.profileImages = Arrays.stream(result.getProfileImages().split(",")).map(Long::valueOf).collect(Collectors.toList());
        this.status = result.getStatus().name();
    }
}
