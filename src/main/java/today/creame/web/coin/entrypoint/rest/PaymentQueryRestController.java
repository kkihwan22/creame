package today.creame.web.coin.entrypoint.rest;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.coin.entrypoint.rest.io.PaymentDetailResponse;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@RestController
public class PaymentQueryRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(PaymentQueryRestController.class);

    @GetMapping("/api/v1/my-payments/history")
    public ResponseEntity<Body<List<PaymentDetailResponse>>> getMyPaymentHistory(@RequestParam(required = false, defaultValue = "1") int month) {
        Long id = SecurityContextSupporter.get().getId();
        log.debug("member id : {}", id);
        PaymentDetailResponse response = new PaymentDetailResponse(1L, "코인충전", "네이버페이", 110000, LocalDateTime.now());
        return ResponseEntity.ok(BodyFactory.success(Collections.singletonList(response)));
    }
}
