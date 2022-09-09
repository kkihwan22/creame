package today.creame.web.member.entrypoint.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter @ToString
public class PhoneVerifyingRequest {
    @NotBlank(message = "토큰 값을 입력해주세요.")
    @Pattern(regexp = "[0-9]", message = "허용되지 않은 문자가 있습니다.")
    private String token;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Length(min = 11, max = 11, message = "전화번호의 길이는 11글자입니다.")
    @Pattern(regexp = "[0-9]", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    @NotBlank(message = "인증번호를 입력해주세요.")
    @Length(min = 6, max = 6, message = "인증번호의 길이는 6글자입니다.")
    @Pattern(regexp = "[0-9]", message = "허용되지 않은 문자가 있습니다.")
    private String verifyCode;
}
