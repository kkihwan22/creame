package today.creame.web.share.support;

public interface Crypto {

    String encrypt(String src) throws Exception;

    String decrypt(String enc) throws Exception;
}
