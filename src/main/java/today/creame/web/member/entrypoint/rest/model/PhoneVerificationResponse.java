package today.creame.web.member.entrypoint.rest.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter @ToString
public class PhoneVerificationResponse {

    private String token;

    public PhoneVerificationResponse(String token) {
        this.token = token;
    }
}
