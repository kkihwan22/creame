package today.creame.web.share.support;

public class MaskingSupporter {
    private static final String EMAIL_PATTERN = "([\\w.])(?:[\\w.]*)(@.*)";
    private static final String LASTNAME_PATTERN = "(?<=.{0}).";
    private static final String LAST_6_CHAR_PATTERN = "(.{6}$)";

    public static String email(String email) {
        return email.replaceAll(EMAIL_PATTERN, "$1****$2");
    }

    public static String ssn(String ssn) {
        return ssn.replaceAll(LAST_6_CHAR_PATTERN, "******");
    }
}
