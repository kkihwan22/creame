package today.creame.web.matching.entrypoint.event.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.ItemCode;

@Getter
@ToString
public class MatchingCreateEvent {
    private Long mId;
    private String cId;
    private ItemCode itemCode;
    private LocalDateTime eventTime;
    private LocalDateTime start;
    private LocalDateTime end;
    private Integer usingTime;
    private Integer usingAmount;
}

// Matching
// matching 테이블 넣고
// 고객 금액에 차감
// 인플루언스 정산 금액에 더하기
// 인플루언스 stat, 고객 stat