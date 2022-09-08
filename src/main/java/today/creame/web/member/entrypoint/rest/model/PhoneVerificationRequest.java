package today.creame.web.member.entrypoint.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter @ToString
public class PhoneVerificationRequest {

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phoneNumber;

    public PhoneVerificationRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
