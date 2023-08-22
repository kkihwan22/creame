package today.creame.web.faq.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.faq.application.FaqService;
import today.creame.web.faq.application.model.FaqRegisterParameter;
import today.creame.web.faq.application.model.FaqResult;
import today.creame.web.faq.entrypoint.rest.io.FaqRegisterRequest;
import today.creame.web.faq.entrypoint.rest.io.FaqResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class FaqRestController implements BaseRestController {
    private final FaqService faqService;

    @GetMapping("/public/v1/faq/{id}")
    public ResponseEntity<Body<FaqResponse>> getDetail(@PathVariable Long id) {
        FaqResult faqResult = faqService.getDetail(id);
        FaqResponse response = new FaqResponse(faqResult);
        return ResponseEntity.ok(BodyFactory.success(response));
    }

    @PostMapping("/admin/v1/faq")
    public ResponseEntity<Body<SimpleBodyData<String>>> register(@RequestBody @Valid FaqRegisterRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        faqService.register(new FaqRegisterParameter(request));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("ok")));
    }

    @PatchMapping("/admin/v1/faq/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> update(@PathVariable Long id, @RequestBody @Valid FaqRegisterRequest request, BindingResult bindingResult) {
        hasError(bindingResult);

        faqService.update(new FaqRegisterParameter(id, request));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("ok")));
    }

    @DeleteMapping("/admin/v1/faq/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> delete(@PathVariable Long id) {
        faqService.delete(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("ok")));
    }
}
