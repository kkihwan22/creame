package today.creame.web.member.entrypoint.rest.io;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PhoneNumberUpdateRequest {

    @Min(1000000000L)
    @Max(9999999999L)
    private Long token;

    @NotBlank(message = "전화번호를 입력해주세요.")
    @Pattern(regexp = "[0-9]{11}", message = "허용되지 않은 문자가 있습니다.")
    private String phoneNumber;

    public PhoneNumberUpdateRequest(Long token, String phoneNumber) {
        this.token = token;
        this.phoneNumber = phoneNumber;
    }
}
