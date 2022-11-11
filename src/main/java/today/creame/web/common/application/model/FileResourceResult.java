package today.creame.web.common.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.common.domain.FileResource;

@Getter @ToString
public class FileResourceResult {

    private Long id;
    private String fileResourceUri;

    public FileResourceResult(FileResource fileResource) {
        this.id = fileResource.getId();
        this.fileResourceUri = fileResource.buildUri();
    }
}
