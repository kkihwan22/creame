package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@ToString
public class InfluenceQnaQueryParameter {
    public Long requesterId;
    public Pageable pageable;

    public InfluenceQnaQueryParameter(int page, int size, Sort orders, Long requesterId) {
        if (page < 1) page = 0;
        if (size < 1) size = 20;
        this.pageable = PageRequest.of(page, size, orders);
        this.requesterId = requesterId;
    }
}