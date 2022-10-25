package today.creame.web.member.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString
public class PhoneVerificationCodeRequest {

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{11}", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    public PhoneVerificationCodeRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
