package today.creame.web.common.entrypoints.rest;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.domain.Item;
import today.creame.web.ranking.domain.ConsultationProduct;
import today.creame.web.ranking.domain.ConsultationProductJpaRepository;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

@RequiredArgsConstructor
@RestController
public class CommonRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(CommonRestController.class);
    private final ConsultationProductJpaRepository repository;


    @GetMapping("/public/v1/commons/item-code")
    public ResponseEntity<Body<List<Item>>> getItemMap() {
        List<ConsultationProduct> consultationProducts = repository.findConsultationProductsByRankingIdOrderByOrderNo(0L);
        List<Item> results = consultationProducts.stream()
                .map(c -> new Item(c.getId(), c.getOrderNo(), c.getBudgetAmount(), 30, TimeUnit.SECONDS))
                .collect(Collectors.toList());
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }
}