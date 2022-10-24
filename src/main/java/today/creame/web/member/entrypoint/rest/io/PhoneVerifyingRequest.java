package today.creame.web.member.entrypoint.rest.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@Getter @ToString
public class PhoneVerifyingRequest {
    @NotBlank(message = "토큰 값을 입력해주세요.")
    private String token;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{11}", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    @NotBlank(message = "인증번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{6}", message = "허용되지 않은 문자가 있습니다.")
    private String verifyCode;
}
