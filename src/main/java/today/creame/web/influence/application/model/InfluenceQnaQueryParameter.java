package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@ToString
public class InfluenceQnaQueryParameter {
    public Long requesterId;
    public Long influenceId;
    public Boolean answered;
    public Pageable pageable;

    public InfluenceQnaQueryParameter(Pageable pageable, Long requesterId, Long influenceId, Boolean answered) {
        this.pageable = pageable;
        this.requesterId = requesterId;
        this.influenceId = influenceId;
        this.answered = answered;
    }
}