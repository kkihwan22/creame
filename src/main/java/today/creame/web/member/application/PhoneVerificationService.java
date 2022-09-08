package today.creame.web.member.application;

public interface PhoneVerificationService {

    String requestCode(String phoneNumber);
}
