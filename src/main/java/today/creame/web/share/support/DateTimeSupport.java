package today.creame.web.share.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateTimeSupport {

    public static Date duration(long milliseconds) {
        Date expiration = new Date();
        long currentTimeMillis = expiration.getTime();
        expiration.setTime(currentTimeMillis + milliseconds);
        return expiration;
    }

    public String formatToDateTime(String format) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
    }
}
