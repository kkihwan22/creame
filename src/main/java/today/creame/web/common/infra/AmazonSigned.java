package today.creame.web.common.infra;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import today.creame.web.share.support.DateTimeSupport;

@RequiredArgsConstructor
@Component
public class AmazonSigned implements Signed{
    private final Logger log = LoggerFactory.getLogger(AmazonSigned.class);
    private final AmazonS3 amazonS3;

    private final static String BUKET_NAME = "today.creame.file";
    private final static long milliseconds = 1000 * 60;

    @Override
    public URL upload(String objectKey) {
        return this.generate(objectKey, HttpMethod.PUT);
    }

    @Override
    public URL download(String objectKey) {
        return this.generate(objectKey, HttpMethod.GET);
    }

    private URL generate(String objectKey, HttpMethod method) {
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(BUKET_NAME, objectKey)
            .withMethod(method)
            .withExpiration(DateTimeSupport.duration(milliseconds));

        request.addRequestParameter(
            Headers.S3_CANNED_ACL,
            CannedAccessControlList.PublicRead.toString()
        );

        return amazonS3.generatePresignedUrl(request);
    }

}
