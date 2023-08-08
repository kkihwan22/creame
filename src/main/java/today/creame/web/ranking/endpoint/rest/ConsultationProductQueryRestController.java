package today.creame.web.ranking.endpoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.ranking.application.ConsultationProductQuery;
import today.creame.web.ranking.domain.ConsultationProduct;
import today.creame.web.ranking.endpoint.rest.io.ConsultationProductResponse;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class ConsultationProductQueryRestController {
    private final ConsultationProductQuery consultationProductQuery;

    @GetMapping("/public/v1/consultation-products")
    public ResponseEntity<Body<List<ConsultationProductResponse>>> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
        Page<ConsultationProduct> results = consultationProductQuery.list(pageable);
        List<ConsultationProductResponse> responses = results.stream().map(ConsultationProductResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(BodyFactory.success(responses));
    }

}
