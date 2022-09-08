package today.creame.web.sms.application;

public interface SmsService {

    void send(String phoneNumber, String content);

}
