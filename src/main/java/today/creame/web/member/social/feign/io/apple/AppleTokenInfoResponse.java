package today.creame.web.member.social.feign.io.apple;

import lombok.Getter;

@Getter
public class AppleTokenInfoResponse {
    String iss;
    String aud;
    String exp;
    String iat;
    String sub;
    String c_hash;
    String email;
    Boolean email_verified;
    Boolean is_private_email;
    Long auth_time;
    Boolean nonce_supported;
    String at_hash;
    String name;
}

