package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Content;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.share.support.SecurityContextSupporter;

import java.time.LocalDateTime;

@Getter @ToString
public class InfluenceQuestionResult {
    private Long id;
    private Long questionerId;
    private String nickname;
    private InfluenceSimpleResult influence;
    private boolean answered;
    private boolean me;
    private Content question;
    private Content answer;
    private LocalDateTime createdDateTime;

    public InfluenceQuestionResult(InfluenceQna qna) {
        this.id = qna.getId();
        this.questionerId = qna.getQuestioner().getId();
        this.nickname = qna.getQuestioner().getNickname();
        this.influence = new InfluenceSimpleResult(qna.getInfluence());
        this.answered = qna.isAnswered();
        this.question = qna.getQuestions();
        this.answer = qna.getAnswers();
        this.createdDateTime = qna.getCreatedDateTime();
        this.me = questionerId.equals(SecurityContextSupporter.orElseGetEmpty());
    }

    public void processContentSecurity() {

        this.question = Content.secretContent(question, me);
        this.answer = Content.secretContent(answer, me);
    }
}