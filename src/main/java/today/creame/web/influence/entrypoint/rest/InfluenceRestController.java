package today.creame.web.influence.entrypoint.rest;

import static today.creame.web.share.domain.OnOffStatus.OFF;
import static today.creame.web.share.domain.OnOffStatus.ON;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.influence.application.InfluenceConnectedService;
import today.creame.web.influence.application.InfluenceNoticeService;
import today.creame.web.influence.application.InfluenceSnsService;
import today.creame.web.influence.domain.SNS;
import today.creame.web.influence.entrypoint.rest.io.NoticeGetResponse;
import today.creame.web.influence.entrypoint.rest.io.NoticeUpdateRequest;
import today.creame.web.influence.entrypoint.rest.io.SnsGetResponse;
import today.creame.web.influence.entrypoint.rest.io.SnsUpdateRequest;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceRestController.class);
    private final InfluenceSnsService influenceSnsService;
    private final InfluenceNoticeService influenceNoticeService;
    private final InfluenceConnectedService influenceConnectedService;

    @GetMapping("/api/v1/influence/{id}/products")
    public void getInfluenceProduct(
        @PathVariable Long id
    ) {

    }

    // 인사말 upload -> 이건 여기가 아닌 다른 곳 (admin?)

    // 상담상품관리 (조회, 수정)

    // 후기 조회 조회 (답변달기)

    // 1:1 문의 조회 (답변달기)

    @GetMapping("/api/v1/influence/{id}/notice")
    public ResponseEntity<Body<NoticeGetResponse>> getNotice(@PathVariable Long id) {
        String notice = influenceNoticeService.get(id);
        return ResponseEntity.ok(BodyFactory.success(new NoticeGetResponse(notice)));
    }

    @PutMapping("/api/v1/influence/{id}/notice")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateNotice(
        @PathVariable Long id,
        @Valid @RequestBody NoticeUpdateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        influenceNoticeService.update(id, request.getNotice());

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/api/v1/influence/{id}/sns")
    public ResponseEntity<Body<SnsGetResponse>> getSns(@PathVariable Long id) {
        SNS sns = influenceSnsService.get(id);
        return ResponseEntity.ok(BodyFactory.success(new SnsGetResponse(sns)));
    }

    @PutMapping("/api/v1/influence/{id}/sns")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateSns(
        @PathVariable Long id,
        @Valid @RequestBody SnsUpdateRequest request, BindingResult bindingResult) {
        hasError(bindingResult);
        influenceSnsService.update(id, new SNS(request.getSnsCompany(), request.getAddress()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }


    @PatchMapping("/api/v1/influence/{id}/connection/on")
    public ResponseEntity<Body<SimpleBodyData<String>>> onConnection(@PathVariable Long id) {
        influenceConnectedService.patchConnectionStatus(id, ON);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/influence/{id}/connection/off")
    public ResponseEntity<Body<SimpleBodyData<String>>> offConnection(@PathVariable Long id) {
        influenceConnectedService.patchConnectionStatus(id, OFF);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}