package today.creame.web.ranking.endpoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.ranking.application.ConsultationProductService;
import today.creame.web.ranking.application.model.ConsultationProductRegisterParameter;
import today.creame.web.ranking.application.model.ConsultationProductResult;
import today.creame.web.ranking.application.model.ConsultationProductUpdateParameter;
import today.creame.web.ranking.endpoint.rest.io.ConsultationProductRegisterRequest;
import today.creame.web.ranking.endpoint.rest.io.ConsultationProductResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class ConsultationProductRestController implements BaseRestController {
    private final ConsultationProductService consultationProductService;

    @GetMapping("/admin/v1/consultation-products/{id}")
    public ResponseEntity<Body<ConsultationProductResponse>> get(@PathVariable Long id) {
        ConsultationProductResult result = consultationProductService.get(id);

        return ResponseEntity.ok(BodyFactory.success(new ConsultationProductResponse(result)));
    }

    @PostMapping("/admin/v1/consultation-products")
    public ResponseEntity<Body<SimpleBodyData<Long>>> register(@RequestBody @Valid ConsultationProductRegisterRequest request, BindingResult bindingResult) {
        hasError(bindingResult);
        Long ConsultationProductId = consultationProductService.register(new ConsultationProductRegisterParameter(request));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(ConsultationProductId)));
    }

    @PatchMapping("/admin/v1/consultation-products/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> update(@PathVariable Long id, @RequestBody @Valid ConsultationProductRegisterRequest request, BindingResult bindingResult) {
        hasError(bindingResult);
        consultationProductService.update(new ConsultationProductUpdateParameter(id, request));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("ok")));
    }

    @DeleteMapping("/admin/v1/consultation-products/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> update(@PathVariable Long id) {
        consultationProductService.delete(id);

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("ok")));
    }

}
