package today.creame.web.sms.application;

public class SmsTemplate {
    private final static String INFLUENCE_WELCOME = "[크리미] 축하:)\n인플 상담사로 승인되셨습니다.\n로그인ID: 신청 이메일\n임시비번: %s";

    public static String influenceWelcome(String password) {
        return String.format(SmsTemplate.INFLUENCE_WELCOME, password);
    }

}
