package today.creame.web.sms.application;

public class SmsTemplate {
    private final static String INFLUENCE_WELCOME = "[크리미] 축하:)\n인플 상담사로 승인되셨습니다.\nID: %s\nPW: %s";
    private final static String PASSWORD_REISSUED = "[크리미] 임시비밀번호가 발급되었습니다.\nPW: %s";

    public static String influenceWelcome(String email, String password) {
        return String.format(SmsTemplate.INFLUENCE_WELCOME, email, password);
    }

    public static String passwordReissued(String password) {
        return String.format(SmsTemplate.PASSWORD_REISSUED, password);
    }

}
