package today.creame.web.sms.application;

public class SmsTemplate {
    private final static String INFLUENCE_WELCOME = "[크리미] 축하:)\n인플 상담사로 승인되셨습니다.\n로그인ID: %s\n임시비번: %s";

    public static String influenceWelcome(String email, String password) {
        return String.format(SmsTemplate.INFLUENCE_WELCOME, email, password);
    }

}
