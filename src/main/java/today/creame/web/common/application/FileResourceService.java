package today.creame.web.common.application;

import org.springframework.web.multipart.MultipartFile;
import today.creame.web.common.application.model.FileResourceResult;

public interface FileResourceService {

    FileResourceResult temp(MultipartFile file);
}
