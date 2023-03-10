package today.creame.web.payments.entrypoint.rest;

import java.util.Arrays;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.payments.application.AutoChargingService;
import today.creame.web.payments.entrypoint.rest.io.AutoChargingCreditCardRegisterRequest;
import today.creame.web.payments.entrypoint.rest.io.CreditCardRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.exception.BadRequestParameterException;
import today.creame.web.share.exception.model.ErrorBodyData;

@RequiredArgsConstructor
@RestController
public class AutoPaymentRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(AutoPaymentRestController.class);
    private final AutoChargingService autoChargingService;

    @PostMapping("/api/v1/me/credit-card/register")
    public void registerCreditCard(
        @RequestBody @Valid AutoChargingCreditCardRegisterRequest request, BindingResult bindingResult
    ) {
        log.debug("request: {}", request);
        hasError(bindingResult);
        CreditCardRequest card = request.getCreditCard();
        if (card.isNotValidSerial()) {
            throw new BadRequestParameterException(Arrays.asList(new ErrorBodyData("card.serial", "카드번호의 길이가 유효하지 않습니다.")));
        }

//        autoChargingService.register(new AutoChargingParameter(
//            new AutoPaymentPreference(10000, request.getAmount()),
//            new CreditCard(card.getSerial(), card.getYear(), card.getMonth(), card.getSocialId(), card.getPassword(), card.getName())
//        ));
    }
}
