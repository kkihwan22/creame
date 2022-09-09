package today.creame.web.member.entrypoint.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString
public class PhoneVerificationCodeResponse {

    private String token;

    public PhoneVerificationCodeResponse(String token) {
        this.token = token;
    }
}
