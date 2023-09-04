package today.creame.web.influence.entrypoint.rest;

import static today.creame.web.share.domain.OnOffCondition.OFF;
import static today.creame.web.share.domain.OnOffCondition.ON;

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
import today.creame.web.influence.application.InfluenceNoticeService;
import today.creame.web.influence.application.InfluenceService;
import today.creame.web.influence.application.model.*;
import today.creame.web.influence.domain.Item;
import today.creame.web.influence.domain.SNS;
import today.creame.web.influence.entrypoint.rest.io.*;
import today.creame.web.share.entrypoint.BaseRestController;
import today.creame.web.share.entrypoint.Body;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.SimpleBodyData;

@RequiredArgsConstructor
@RestController
public class InfluenceRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceRestController.class);
    private final InfluenceService influenceService;
    private final InfluenceNoticeService influenceNoticeService;

    @GetMapping("/api/v1/influence/{id}/notice")
    public ResponseEntity<Body<InfluenceNoticeResult>> getNotice(@PathVariable Long id) {
        InfluenceNoticeResult result = influenceNoticeService.get(id);
        return ResponseEntity.ok(BodyFactory.success(result));
    }

    @PutMapping("/api/v1/influence/{id}/notice")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateNotice(
        @PathVariable Long id,
        @Valid @RequestBody NoticeUpdateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        influenceNoticeService.createOrUpdate(new InfluenceNoticeParameter(id, request.getContent(), request.getAttachFiles()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/api/v1/influence/{id}/sns")
    public ResponseEntity<Body<SnsGetResponse>> getSns(@PathVariable Long id) {
        SNS sns = influenceService.getSNS(id);
        return ResponseEntity.ok(BodyFactory.success(new SnsGetResponse(sns)));
    }

    @PutMapping("/api/v1/influence/{id}/sns")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateSns(
        @PathVariable Long id,
        @Valid @RequestBody SnsUpdateRequest request, BindingResult bindingResult) {
        hasError(bindingResult);
        influenceService.update(new SnsParameter(id, new SNS(request.getSnsCompany(), request.getAddress())));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/influence/{id}/connection/on")
    public ResponseEntity<Body<SimpleBodyData<String>>> onConnection(@PathVariable Long id) {
        influenceService.patchConnectionStatus(id, ON);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/influence/{id}/connection/off")
    public ResponseEntity<Body<SimpleBodyData<String>>> offConnection(@PathVariable Long id) {
        influenceService.patchConnectionStatus(id, OFF);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @GetMapping("/api/v1/influences/{id}/item")
    public ResponseEntity<Body<SimpleBodyData<Integer>>> selectedItemIndex(
        @PathVariable Long id
    ) {
        Item item = influenceService.getItem(new InfluenceItemParameter(id));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>(item.getIndex())));
    }

    @PatchMapping("/api/v1/influences/{id}/item/{index}")
    public ResponseEntity<Body<SimpleBodyData<String>>> changeItem(
        @PathVariable Long id,
        @PathVariable Long index
    ) {
        influenceService.changeItem(new InfluenceItemParameter(id, index));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PutMapping("/admin/v1/influence/{id}/blocked")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateBlocked(@PathVariable Long id) {
        influenceService.updateBlocked(id);
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PutMapping("/admin/v1/influence/{id}/profile-images")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateProfileImages(@PathVariable Long id, @RequestBody InfluenceProfileImageUpdateRequest request) {
        influenceService.updateProfileImages(new InfluenceProfileImageUpdateParameter(id, request.getCreateFileResourceIds(), request.getDeleteFileResourceIds()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PatchMapping("/api/v1/me/influence/changed-expose-status")
    public ResponseEntity<Body<SimpleBodyData<String>>> changedExposeStatus(@RequestBody InfluenceChangedExposeStatusRequest request) {
        influenceService.changedExposeStatus(request.isStatus());
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PutMapping("/admin/v1/influence/{id}/notice")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateNoticeByAdmin(
            @PathVariable Long id,
            @Valid @RequestBody NoticeAdminUpdateRequest request, BindingResult bindingResult
    ) {
        hasError(bindingResult);
        influenceNoticeService.createOrUpdateByAdmin(new InfluenceAdminNoticeParameter(id, request.getContent(), request.getAttachFiles()));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }

    @PutMapping("/admin/v1/influence/{id}")
    public ResponseEntity<Body<SimpleBodyData<String>>> updateInfluenceInfo(@PathVariable Long id, @RequestBody InfluenceUpdateInfoRequest request) {
        influenceService.updateInfluenceInfo(new InfluenceUpdateInfoParameter(id, request));
        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData<>("success")));
    }
}