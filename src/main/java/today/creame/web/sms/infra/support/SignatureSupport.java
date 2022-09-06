package today.creame.web.sms.infra.support;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

public class SignatureSupport {
    private final static Logger log = LoggerFactory.getLogger(SignatureSupport.class);
    private final static char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    enum AlgorithmCode {
        HMAC_SHA256("HmacSHA256"), HMAC_MD5("HmacMD5");

        @Getter
        private String code;

        AlgorithmCode(String code) {
            this.code = code;
        }
    }

    public static String generateSignature(String src, LocalDateTime timestamp, String salt) {
        SecretKeySpec keySpec = new SecretKeySpec(src.getBytes(), AlgorithmCode.HMAC_SHA256.code);
        String signature = null;

        try {
            Mac mac = Mac.getInstance(AlgorithmCode.HMAC_SHA256.code);
            mac.init(keySpec);
            signature = bytesToHex(mac.doFinal((timestamp+salt).getBytes()));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            log.error("[ reason ] {}", e);
        }
        return signature;
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hex = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int pos = bytes[i] & 0xff;
            hex[i*2] = HEX_ARRAY[pos >>> 4];
            hex[i*2+1] = HEX_ARRAY[pos & 0x0f];
        }

        return new String(hex);
    }
}