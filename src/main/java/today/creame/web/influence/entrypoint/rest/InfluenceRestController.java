package today.creame.web.influence.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.share.entrypoint.BaseRestController;

@RequiredArgsConstructor
@RestController
public class InfluenceRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceRestController.class);

    @GetMapping("/api/v1/influence/{id}/products")
    public void getInfluenceProduct(
        @PathVariable Long id
    ) {

    }

    // 인사말 upload -> 이건 여기가 아닌 다른 곳 (admin?)

    // 상담상품관리 (조회, 수정)

    // 공지 (조회 / 입력 / 수정)

    // SNS 관리

    // 후기 조회 조회 (답변달기)

    // 1:1 문의 조회 (답변달기)



    @PatchMapping("/api/v1/influence/{id}/connection/on")
    public void onConnection(
        @PathVariable Long id
    ) {

    }

    @PatchMapping("/api/v1/influence/{id}/connection/off")
    public void offConnection(
        @PathVariable Long id
    ) {

    }
}
