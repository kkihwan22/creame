package today.creame.web.sms.application;

public interface SmsSender {

    void sendOne(String phoneNumber, String text);
}
