package today.creame.web.member.application;

public interface PhoneVerificationService {

    void verifyCode(String token, String phoneNumber, String code);
    Long requestCode(String phoneNumber);
}
