package today.creame.web.matching.entrypoint.rest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.matching.entrypoint.rest.io.MyMatchingResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@RestController
public class MatchingQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(MatchingQueryRestController.class);

    @GetMapping("/api/v1/my-matchings")
    public ResponseEntity<Body<List<MyMatchingResponse>>> getMyMatchings(
        @RequestParam(required = false, defaultValue = "1") int month
    ) {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);

        List<MyMatchingResponse> results = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime end = now.plusMinutes(30);
        MyMatchingResponse result1 = new MyMatchingResponse(
            1L,
            now,
            end,
            50000,
            57L,
            "57",
            "안느",
            "https://s3.ap-northeast-2.amazonaws.com/today.creame.file/temp/20221104/20221104164910-10089.jpg"

        );

        MyMatchingResponse result2 = new MyMatchingResponse(
            2L,
            now,
            end,
            50000,
            52L,
            "52",
            "씨제이미초",
            "https://s3.ap-northeast-2.amazonaws.com/today.creame.file/temp/20221104/20221104164910-10089.jpg"
        );

        MyMatchingResponse result3 = new MyMatchingResponse(
            3L,
            now,
            end,
            50000,
            51L,
            "51",
            "남자연예인",
            "https://s3.ap-northeast-2.amazonaws.com/today.creame.file/temp/20221104/20221104164910-10089.jpg"
        );

        results.add(result3);
        results.add(result2);
        results.add(result1);
        return ResponseEntity.ok(BodyFactory.success(results));
    }
}
