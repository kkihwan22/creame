package today.creame.web.pages.entrypoint;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class PageController {
    private final Logger log = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/pages/content")
    public String pageContent() {
        log.debug("page view/content.html");
        return "view/content";
    }
}
