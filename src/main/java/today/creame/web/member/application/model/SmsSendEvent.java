package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class SmsSendEvent {

    private String phoneNumber;
    private String digit;

    public SmsSendEvent(String phoneNumber, String digit) {
        this.phoneNumber = phoneNumber;
        this.digit = digit;
    }
}
