package today.creame.web.common.entrypoints.rest;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import today.creame.web.common.application.FileResourceService;
import today.creame.web.common.exception.NotExistFileResourceException;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

@RequiredArgsConstructor
@RestController
public class FileResourceRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(FileResourceRestController.class);
    private final FileResourceService fileResourceService;

    @PostMapping("/public/v1/temp-profile-image")
    public ResponseEntity<Body<List<Long>>> tempProfileImages(@RequestPart(name = "files") MultipartFile[] files) {
        if (files.length < 1) {
            log.info(" Not exist files. ");
            throw new NotExistFileResourceException();
        }

        if (files.length > 4) {
            log.info(" Not exist files. ");
            throw new NotExistFileResourceException();
        }

        List<Long> results = new ArrayList<>();
        for (MultipartFile file : files) {
            results.add(fileResourceService.temp(file));
        }

        log.debug("results:{}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }
}
