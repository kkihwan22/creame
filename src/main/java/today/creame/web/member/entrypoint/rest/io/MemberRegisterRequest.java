package today.creame.web.member.entrypoint.rest.io;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.application.model.MemberRegisterParameter;

@Getter
@ToString
public class MemberRegisterRequest {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{11}", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    @NotNull(message = "토큰 값을 입력해주세요.")
    @Size(min = 10, max = 10)
    private String phoneVerifiedCode;

    public MemberRegisterParameter toParameter() {
        return new MemberRegisterParameter(email, nickname, password, phoneNumber, Long.parseLong(phoneVerifiedCode));
    }
}