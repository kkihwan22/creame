package today.creame.web.member.application;

public interface PhoneVerificationService {

    void verify(Long token, String phoneNumber, Integer code);

    Long requestCode(String phoneNumber);

    boolean isVerified(Long token);
}
