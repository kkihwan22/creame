package today.creame.web.sms.entrypoint.listener.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class SmsSendEvent {

    private final String phoneNumber;
    private final String body;

    public SmsSendEvent(String phoneNumber, String body) {
        this.phoneNumber = phoneNumber;
        this.body = body;
    }
}