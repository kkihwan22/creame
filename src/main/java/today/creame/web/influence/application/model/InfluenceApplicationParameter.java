package today.creame.web.influence.application.model;

import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.domain.InfluenceApplicationStatus;
import today.creame.web.share.model.BaseParameter;

@Getter @ToString
public class InfluenceApplicationParameter implements BaseParameter<InfluenceApplication> {

    private String name;
    private String nickname;
    private String email;
    private String phoneNumber;
    private String introduction;
    private List<Category> categories;
    private List<Long> profileImages;


    public InfluenceApplicationParameter(String name, String nickname, String email, String phoneNumber, String introduction, List<Category> categories, List<Long> profileImages) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;
        this.categories = categories;
        this.profileImages = profileImages;
    }


    @Override
    public InfluenceApplication toEntity() {
        return new InfluenceApplication(
            null,
            this.name,
            this.nickname,
            this.phoneNumber,
            this.email,
            this.introduction,
            this.categories.stream()
                .map(Category::name)
                .collect(Collectors.joining(",")),
            this.profileImages.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")),
            InfluenceApplicationStatus.REQUEST
        );
    }
}
