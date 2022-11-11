package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter @ToString
public class InfluenceQnaQueryParameter {
    public Pageable pageable;
    public boolean me;
    public Long requesterId;

    public InfluenceQnaQueryParameter(int page, int size, Sort orders, boolean me, Long requesterId) {
        this.pageable = PageRequest.of(page, size, orders);
        this.me = me;
        this.requesterId = requesterId;
    }
}