package today.creame.web.influence.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import today.creame.web.influence.application.InfluenceQnaService;
import today.creame.web.influence.application.InfluenceQuery;
import today.creame.web.influence.application.model.InfluenceAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaSearchParameter;
import today.creame.web.influence.application.model.InfluenceQuestionResult;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.influence.entrypoint.rest.io.InfluenceQnaListResponse;
import today.creame.web.influence.entrypoint.rest.io.InfluenceQnaSearchRequest;
import today.creame.web.influence.entrypoint.rest.io.QuestionContentRequest;
import today.creame.web.influence.entrypoint.rest.io.QuestionListResponse;
import today.creame.web.share.entrypoint.*;
import today.creame.web.share.support.SecurityContextSupporter;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class InfluenceQnaRestController implements BaseRestController {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaRestController.class);
    private final InfluenceQnaService influenceQnaService;
    private final InfluenceQuery influenceQuery;

    @GetMapping("/api/v1/me/questions")
    public ResponseEntity<Body<QuestionListResponse>> getMyQuestions() {
        Long questionerId = SecurityContextSupporter.getId();
        List<InfluenceQuestionResult> results = influenceQuery.getMyQuestions(questionerId);
        Map<Boolean, List<InfluenceQuestionResult>> partition = results.stream().collect(Collectors.partitioningBy(InfluenceQuestionResult::isAnswered));
        return ResponseEntity.ok(BodyFactory.success(new QuestionListResponse(partition)));
    }

    @GetMapping("/api/v1/me/influence/questions")
    public ResponseEntity<Body<QuestionListResponse>> getInfluenceQuestions() {
        Long influenceId = SecurityContextSupporter.getId();
        List<InfluenceQuestionResult> results = influenceQuery.getInfluenceQuestions(influenceId);
        Map<Boolean, List<InfluenceQuestionResult>> partition = results.stream().collect(Collectors.partitioningBy(InfluenceQuestionResult::isAnswered));
        return ResponseEntity.ok(BodyFactory.success(new QuestionListResponse(partition)));
    }

    @GetMapping("/public/v1/influences/{id}/questions")
    public ResponseEntity<Body<List<InfluenceQuestionResult>>> getQuestionsByInfluence(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "20") Integer size) {
        List<InfluenceQuestionResult> results = influenceQuery.getQuestionsByInfluence(id, PageRequest.of(page, size));
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @GetMapping("/api/v1/influences/{id}/questions/me")
    public ResponseEntity<Body<List<InfluenceQuestionResult>>> getInfluenceQuestionsByMe(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "20") Integer size) {
        Long questionerId = SecurityContextSupporter.getId();
        List<InfluenceQuestionResult> results = influenceQuery.getInfluenceQuestionsByMe(id, questionerId, PageRequest.of(page, size));
        log.debug("results: {}", results);
        return ResponseEntity.ok(BodyFactory.success(results));
    }

    @PostMapping("/api/v1/influences/{id}/questions")
    public ResponseEntity<Body<SimpleBodyData<String>>> createQuestion(
            @PathVariable Long id,
            @RequestBody @Valid QuestionContentRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        this.hasError(bindingResult);
        influenceQnaService.question(request.toParameter(id, SecurityContextSupporter.getId()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData("success")));
    }

    @PutMapping("/api/v1/questions/{id}/answer")
    public ResponseEntity<Body<SimpleBodyData<String>>> createAnswer(
            @PathVariable Long id,
            @RequestBody @Valid QuestionContentRequest request, BindingResult bindingResult) {
        log.debug("request: {}", request);
        this.hasError(bindingResult);
        influenceQnaService.answer(new InfluenceAnswerParameter(SecurityContextSupporter.getId(), id, request.getContent()));

        return ResponseEntity.ok(BodyFactory.success(new SimpleBodyData("success")));
    }

    @GetMapping("/admin/v1/influence/questions")
    public ResponseEntity<PageBody<InfluenceQnaListResponse>> list(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable, InfluenceQnaSearchRequest request) {
        Page<InfluenceQna> matchingPage = influenceQuery.qnaList(new InfluenceQnaSearchParameter(request), pageable);
        List<InfluenceQnaListResponse> responses = CollectionUtils.emptyIfNull(matchingPage.getContent()).stream().map(InfluenceQnaListResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(BodyFactory.success(responses, matchingPage.getTotalElements()));
    }
}
