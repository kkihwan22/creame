package today.creame.web.faq.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.creame.web.faq.domain.FaqCategory;

@Getter
@AllArgsConstructor
public class FaqSearchRequest {
    private FaqCategory category;
}
