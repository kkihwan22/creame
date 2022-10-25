package today.creame.web.influence.application.model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.share.model.BaseParameter;

@Getter @ToString
public class InfluenceCreateParameter implements BaseParameter<Influence> {

    private Long memberId;
    private String name;
    private String nickname;
    private String phoneNumber;
    private String email;
    private String introduction;
    private List<String> categories;
    private List<Long> profileImages;

    public InfluenceCreateParameter(Long memberId, InfluenceApplication application) {
        this.memberId = memberId;
        this.name = application.getName();
        this.nickname = application.getNickname();
        this.phoneNumber = application.getPhoneNumber();
        this.email = application.getEmail();
        this.introduction = application.getIntroduction();

        this.categories = Arrays
            .stream(application.getCategories().split(","))
            .collect(Collectors.toList());

        this.profileImages = Arrays
            .stream(application.getProfileImages().split(","))
            .map(Long::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    public Influence toEntity() {
        return new Influence(memberId, name, nickname, phoneNumber, email, introduction);
    }
}
