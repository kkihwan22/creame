package today.creame.web.notice.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.notice.application.NoticeService;
import today.creame.web.notice.application.model.NoticeRegisterParameter;
import today.creame.web.notice.application.model.NoticeResult;
import today.creame.web.notice.entrypoint.rest.io.NoticeRegisterRequest;
import today.creame.web.notice.entrypoint.rest.io.NoticeResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class NoticeRestController implements BaseRestController {
    private final NoticeService noticeService;

    @GetMapping("/public/v1/notices/{id}")
    public ResponseEntity<Body<NoticeResponse>> getDetail(@PathVariable Long id) {
        NoticeResult noticeResult = noticeService.getDetail(id);
        NoticeResponse response = new NoticeResponse(noticeResult);
        return ResponseEntity.ok(BodyFactory.success(response));
    }

    @PostMapping("/admin/v1/notices")
    public ResponseEntity<Body<SimpleBodyData<Long>>> register(@RequestBody @Valid NoticeRegisterRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        Long noticeId = noticeService.register(new NoticeRegisterParameter(request));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(noticeId)));
    }

    @PatchMapping("/admin/v1/notices/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> update(@PathVariable Long id, @RequestBody @Valid NoticeRegisterRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        noticeService.update(new NoticeRegisterParameter(id, request));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @DeleteMapping("/admin/v1/notices/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> delete(@PathVariable Long id) {
        noticeService.delete(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

}
