package today.creame.web.member.application.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@ToString
public class MyQuestionsQueryParameter {
    private Pageable pageable;
    private boolean answered;

    public MyQuestionsQueryParameter(int size, int page, boolean answered) {
        this.pageable = PageRequest.of(size, page);
        this.answered = answered;
    }
}
