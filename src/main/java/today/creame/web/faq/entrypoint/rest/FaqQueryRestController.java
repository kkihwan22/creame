package today.creame.web.faq.entrypoint.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import today.creame.web.faq.application.FaqQuery;
import today.creame.web.faq.application.model.FaqSearchParameter;
import today.creame.web.faq.domain.Faq;
import today.creame.web.faq.entrypoint.rest.io.FaqResponse;
import today.creame.web.faq.entrypoint.rest.io.FaqSearchRequest;
import today.creame.web.share.entrypoint.BodyFactory;
import today.creame.web.share.entrypoint.PageBody;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class FaqQueryRestController {
    private final FaqQuery faqQuery;

    @GetMapping("/public/v1/faqs")
    public ResponseEntity<PageBody<FaqResponse>> list(FaqSearchRequest searchRequest, Pageable pageable){

        Page<Faq> faqPage = faqQuery.list(new FaqSearchParameter(searchRequest), pageable);
        List<FaqResponse> responses = faqPage.stream().map(FaqResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(BodyFactory.success(responses, faqPage.getTotalElements()));
    }


}
