package today.creame.web.member.application;

public interface PhoneVerificationService {

    void verify(String token, String phoneNumber, Integer code);

    String requestCode(String phoneNumber);

    boolean isVerified(String token);
}
