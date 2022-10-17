package today.creame.web.suppoter;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JasyptUtil {
    private final static Logger log = LoggerFactory.getLogger(JasyptUtil.class);

    @Qualifier("jasyptStringEncryptor")
    @Autowired
    private StringEncryptor encryptor;


    @Test
    void jasypt() {
        String url = "";
        String username = "developer";
        String password = "7gv3y3#j+Xc6";

        String smsApiKey = "NCSZMUTYDUZDROYQ";
        String smsSecretKey = "T5LJMVQPY4EORYKNOTM5QYHVQ4CZFW13";

        String awsApiKey = "AKIAZYVZOIJOX4QIK66X";
        String awsSecretKey = "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";


        log.info("ENC UserName : {}", encryptor.encrypt(username));
        log.info("ENC Password : {}", encryptor.encrypt(password));
        log.info("ENC smsApiKey : {}", encryptor.encrypt(smsApiKey));
        log.info("ENC smsSecretKey : {}", encryptor.encrypt(smsSecretKey));
        log.info("ENC awsApiKey : {}", encryptor.encrypt(awsApiKey));
        log.info("ENC awsSecretKey : {}", encryptor.encrypt(awsSecretKey));
    }
}