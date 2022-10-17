package today.creame.web.common.infra;

import java.net.URL;

public interface Signed {

    URL upload(String objectKey);
    URL download(String objectKey);
}
