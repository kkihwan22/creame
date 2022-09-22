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
import today.creame.web.member.entrypoint.rest.model.PhoneVerificationCodeRequest;
import today.creame.web.member.entrypoint.rest.model.PhoneVerificationCodeResponse;
import today.creame.web.member.entrypoint.rest.model.PhoneVerifyingRequest;
import today.creame.web.share.entrypoint.ResponseBody;
import today.creame.web.share.entrypoint.ResponseBodyFactory;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PhoneVerificationRestController {
    private final Logger log = LoggerFactory.getLogger(PhoneVerificationRestController.class);
    private final PhoneVerificationService phoneVerificationService;

    @PostMapping("/public/v1/phone-verification")
    public ResponseEntity<ResponseBody<Void>> verifyCode(
            @RequestBody @Valid
            PhoneVerifyingRequest request,
            BindingResult bindingResult) {

        log.debug("request: {}", request);

        // TODO: Bad Request 처리 구체화
        if (bindingResult.hasErrors()) {
            log.info(" [ Bad Request ] error count : {}", bindingResult.getErrorCount());
        }

        phoneVerificationService.verifyCode(request.getToken(), request.getPhoneNumber(), request.getVerifyCode());

        return ResponseEntity.ok(ResponseBodyFactory.success(null));
    }

    @PostMapping("/public/v1/phone-verification/code-request")
    public ResponseEntity<ResponseBody<PhoneVerificationCodeResponse>> requestCode(
            @RequestBody @Valid
            PhoneVerificationCodeRequest request,
            BindingResult bindingResult) {

        log.debug("request: {} ", request);

        if (bindingResult.hasErrors()) {
            log.info(" [ Bad Request ] error count : {}", bindingResult.getErrorCount());
        }

        Long token = phoneVerificationService.requestCode(request.getPhoneNumber());

        log.debug("token : {}", token);

        return ResponseEntity.ok(ResponseBodyFactory.success(new PhoneVerificationCodeResponse(Long.toString(token))));
    }
}