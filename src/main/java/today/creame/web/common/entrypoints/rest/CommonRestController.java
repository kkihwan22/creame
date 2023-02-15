package today.creame.web.common.entrypoints.rest;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.domain.Item;
import today.creame.web.influence.domain.ItemMap;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;

@RestController
public class CommonRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(CommonRestController.class);

    @GetMapping("/public/v1/commons/item-code")
    public ResponseEntity<Body<List<Item>>> getItemMap() {
        return ResponseEntity.ok(BodyFactory.success(ItemMap.get()));
    }
}