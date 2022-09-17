package today.creame.web.member.entrypoint.rest.model;

import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import today.creame.web.member.application.model.MemberRegisterParameter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter @ToString
public class MemberRegisterRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Length(min = 11, max = 11, message = "전화번호의 길이는 11글자입니다.")
    @Pattern(regexp = "[0-9]", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    @NotBlank(message = "토큰 값을 입력해주세요.")
    @Pattern(regexp = "[0-9]", message = "허용되지 않은 문자가 있습니다.")
    private String token;

    public MemberRegisterParameter to() {
        return new MemberRegisterParameter(email, nickname, password, token, phoneNumber);
    }
}