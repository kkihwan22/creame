package today.creame.web.share.support;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.util.Base64Utils;

public class M2netCrypto implements Crypto {
    private final String key = "6233e926998241790d3500d4"; // TODO: 환경변수로 분리 (application.yml)
    private final Cipher cipher;

    public M2netCrypto() throws Exception {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    @Override
    public String encrypt(String src) throws Exception {
        String encKey = SHA512(key);
        byte[] byteKey = encKey.substring(0, 16).getBytes();
        byte initVector[] = key.getBytes();
        SecretKey secureKey = new SecretKeySpec(byteKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(initVector));
        byte[] encrypted = cipher.doFinal(src.getBytes("UTF-8"));
        // printHEX( encrypted, encrypted.length );

        return Base64Utils.encodeToString(encrypted); // 확인필요!

        //return Base64Utils.base64Encode( encrypted );
    }

    @Override
    public String decrypt(String enc) throws Exception {
        try {
            String encKey = SHA512(key);
            byte[] byteKey = encKey.substring(0, 16).getBytes();
            byte initVector[] = key.getBytes();
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec secretKeySpec = new SecretKeySpec(byteKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);
            byte[] original = cipher.doFinal(Base64Utils.decodeFromString(enc));
            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String SHA512(String str) {
        String SHA = "";
        try {
            MessageDigest sh = MessageDigest.getInstance("SHA-512");
            sh.update(str.getBytes());
            byte byteData[] = sh.digest();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            SHA = null;
        }
        return SHA;
    }
}
