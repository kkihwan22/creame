package today.creame.web.admin.entrypoint;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.admin.application.QnaService;
import today.creame.web.admin.entrypoint.rest.io.QnaCreateRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class QnaRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(QnaRestController.class);
    private final QnaService qnaService;

    @PostMapping("/api/v1/qna")
    public ResponseEntity<Body<SimpleBodyData<String>>> create(
        @Valid @RequestBody QnaCreateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        qnaService.create(request.toParam());
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}
