package today.creame.web.share.support;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class RandomString {
    public static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String lower = upper.toLowerCase(Locale.ROOT);
    public static final String digits = "0123456789";
    public static final String special = "!@#$%^&";
    public static final String alphanum = upper + lower + digits;
    public static final String specialaplanum = alphanum + special;
    private final Random random;
    private final char[] symbols;
    private final char[] buf;

    public RandomString(int length, Random random, String symbols) {
        if (length < 1) throw new IllegalArgumentException();
        if (symbols.length() < 2) throw new IllegalArgumentException();
        this.random = Objects.requireNonNull(random);
        this.symbols = symbols.toCharArray();
        this.buf = new char[length];
    }

    /**
     * Create an alphanumeric string generator.
     */
    public RandomString(int length, Random random) {
        this(length, random, alphanum);
    }

    /**
     * Create an alphanumeric strings from a secure generator.
     */
    public RandomString(int length) {
        this(length, new SecureRandom());
    }

    public RandomString(int length, String symbols) {
        this(length, new Random(), symbols);
    }

    /**
     * Create session identifiers.
     */
    public RandomString() {
        this(21);
    }

    public static RandomString password() {
        return new RandomString(10, new SecureRandom(), specialaplanum);
    }

    public static RandomString token(int length) {
        return new RandomString(length, new SecureRandom(), specialaplanum);
    }

    public String nextString() {
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
}