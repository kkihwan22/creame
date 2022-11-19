package today.creame.web.member.entrypoint.rest.io;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class PhoneVerifyingRequest {
    @NotNull(message = "토큰 값을 입력해주세요.")
    @Min(value = 1000000000L, message = "토큰의 길이는 10자리입니다.")
    @Max(value = 9999999999L, message = "토큰의 길이는 10자리입니다.")
    private Long token;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{11}", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    @NotNull(message = "인증번호를 입력해주세요.")
    @Min(value = 100000, message = "인증번호의 길이는 6자리입니다.")
    @Max(value = 999999, message = "인증번호의 길이는 6자리입니다.")
    private Integer verifyCode;
}
