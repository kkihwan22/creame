package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import today.creame.web.influence.domain.InfluenceQna;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class InfluenceQnaListResponse {
    private Long id;
    private String influenceNickname;
    private String memberNickname;
    private String content;
    private String answer;
    private LocalDateTime answeredDt;
    private boolean answered;
    private LocalDateTime createdDt;
    private LocalDateTime updatedDt;

    public InfluenceQnaListResponse(InfluenceQna influenceQna) {
        this.id = influenceQna.getId();
        this.influenceNickname = influenceQna.getInfluence().getNickname();
        this.memberNickname = influenceQna.getQuestioner().getNickname();
        this.content = influenceQna.getQuestions().getContent();
        this.answer = Objects.isNull(influenceQna.getAnswers()) ? null : influenceQna.getAnswers().getContent();
        this.answeredDt = Objects.isNull(influenceQna.getAnswers()) ? null : influenceQna.getAnswers().getUpdatedDateTime();
        this.answered = influenceQna.isAnswered();
        this.createdDt = influenceQna.getCreatedDateTime();
        this.updatedDt = influenceQna.getUpdatedDateTime();


    }
}
