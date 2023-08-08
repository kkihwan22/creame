package today.creame.web.ranking.endpoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class ConsultationProductRegisterRequest {
    @NotNull(message = "레벨을 선택해주세요")
    private Long rankingId;
    @NotNull(message = "책정할 이용료를 입력해주세요.")
    private int budgetAmounts;
    @NotNull(message = "이용료별 상담료를 입력해주세요")
    private int consultationAmount;
}
