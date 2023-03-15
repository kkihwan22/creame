package today.creame.web.member.entrypoint.rest;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.member.application.PhoneVerificationService;
import today.creame.web.member.entrypoint.rest.io.PhoneVerificationCodeRequest;
import today.creame.web.member.entrypoint.rest.io.PhoneVerifyingRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class PhoneVerificationRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationRestController.class);
    private final PhoneVerificationService phoneVerificationService;

    @PostMapping("/public/v1/phone-verification")
    public ResponseEntity<Body<SimpleBodyData<String>>> verifyCode(
        @RequestBody @Valid PhoneVerifyingRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);

        hasError(bindingResult);

        phoneVerificationService.verify(
            request.getToken(),
            request.getPhoneNumber(),
            request.getVerifyCode()
        );

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PostMapping("/public/v1/phone-verification/code-request")
    public ResponseEntity<Body<SimpleBodyData<String>>> requestCode(
        @RequestBody @Valid PhoneVerificationCodeRequest request, BindingResult bindingResult) {

        log.debug("request: {} ", request);

        if (bindingResult.hasErrors()) {
            log.info(" [ Bad Request ] error count : {}", bindingResult.getErrorCount());
        }

        Long token = phoneVerificationService.requestCode(request.getPhoneNumber());

        log.debug("token : {}", token);

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(String.valueOf(token))));
    }
}