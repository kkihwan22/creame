package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.InfluenceQuestionResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter @ToString
public class QuestionListResponse {

    private int answerableCount;
    private int answeredCount;
    private List<InfluenceQuestionResult> answerableQuestions;
    private List<InfluenceQuestionResult> answeredQuestions;

    public QuestionListResponse(Map<Boolean, List<InfluenceQuestionResult>> partition) {
        Optional.ofNullable(partition.get(Boolean.TRUE)).ifPresent(it -> {
            this.answeredCount = it.size();
            this.answeredQuestions = it;
        });

        Optional.ofNullable(partition.get(Boolean.FALSE)).ifPresent(it -> {
            this.answerableCount = it.size();
            this.answerableQuestions = it;
        });
    }
}
