package today.creame.web.sms.application.adapter;

public interface SmsSender {

    void sendOne(String phoneNumber, String text);
}
