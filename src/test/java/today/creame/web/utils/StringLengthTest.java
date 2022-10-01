package today.creame.web.utils;

import org.junit.jupiter.api.Test;

public class StringLengthTest {

    private final String TEMP = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDUkVBTUUiLCJleHAiOjE2NjYxMDYzNjcsImF1dGhvcml0aWVzIjoiVVNFUiIsInVzZXJuYW1lIjoidXNlcjA5MzBAZ21haWwuY29tIn0.7pmJOuOdxPuejH4Ic5D6Eti-bS2aLtcFJqRNYZBPGoE";

    @Test
    void test_string_length() {

        System.out.println("len: " +TEMP.length());
    }
}
