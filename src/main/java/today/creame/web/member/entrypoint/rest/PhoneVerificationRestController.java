package today.creame.web.member.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.member.application.PhoneVerificationService;
import today.creame.web.member.entrypoint.rest.model.PhoneVerificationRequest;
import today.creame.web.member.entrypoint.rest.model.PhoneVerificationResponse;
import today.creame.web.share.entrypoint.ResponseBody;
import today.creame.web.share.entrypoint.ResponseBodyFactory;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PhoneVerificationRestController {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationRestController.class);
    private final PhoneVerificationService phoneVerificationService;

    @PostMapping("/phone-verification/code-request")
    public ResponseEntity<ResponseBody<PhoneVerificationResponse>> requestCode(
            @RequestBody @Valid
            PhoneVerificationRequest request,
            BindingResult bindingResult) {

        log.debug("request: {} ", request);

        if (bindingResult.hasErrors()) {
            log.info(" [ Bad Request ] error count : {}", bindingResult.getErrorCount());
        }

        String token = phoneVerificationService.requestCode(request.getPhoneNumber());

        log.debug("token: {}", token);

        return ResponseEntity.ok(ResponseBodyFactory.success(new PhoneVerificationResponse(token)));
    }
}