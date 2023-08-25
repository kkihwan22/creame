package today.creame.web.notice.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.notice.application.NoticeQuery;
import today.creame.web.notice.domain.Notice;
import today.creame.web.notice.entrypoint.rest.io.NoticeResponse;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.PageBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class NoticeQueryRestController {
    private final NoticeQuery noticeQuery;
    @GetMapping("/public/v1/notices")
    public ResponseEntity<PageBody<NoticeResponse>> list(@PageableDefault(size = 10)Pageable pageable){

        Page<Notice> noticePage = noticeQuery.list(pageable);
        List<NoticeResponse> responses = noticePage.stream().map(NoticeResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(BodyFactory.success(responses, noticePage.getTotalElements()));
    }
}
