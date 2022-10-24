package today.creame.web.influence.entrypoint.rest.io;

import java.util.List;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Category;
import today.creame.web.influence.application.model.InfluenceApplicationParameter;
import today.creame.web.share.model.BaseRequest;

@Getter @ToString
public class InfluenceApplicationRegisterRequest implements BaseRequest<InfluenceApplicationParameter> {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{11}", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    @NotBlank(message = "본인소개를 입력해주세요.")
    private String introduction;

    @Size(max=4)
    @NotNull
    private List<Category> categories;

    @Size(max=4)
    @NotNull
    private List<Long> profileImageIds;

    @Override
    public InfluenceApplicationParameter toParameter() {
        return new InfluenceApplicationParameter(
            name,
            nickname,
            email,
            phoneNumber,
            introduction,
            categories,
            profileImageIds
        );
    }
}
