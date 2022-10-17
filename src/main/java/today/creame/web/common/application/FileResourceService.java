package today.creame.web.common.application;

import org.springframework.web.multipart.MultipartFile;

public interface FileResourceService {

    Long temp(MultipartFile file);
}
