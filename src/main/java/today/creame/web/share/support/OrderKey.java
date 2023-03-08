package today.creame.web.share.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OrderKey {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private RandomString rs;

    public OrderKey(RandomString rs) {
        this.rs = rs;
    }

    public OrderKey() {
        this(new RandomString(8, RandomString.digits));
    }

    public String next() {
        return LocalDateTime.now().format(FORMAT).concat(rs.nextString());
    }
}
