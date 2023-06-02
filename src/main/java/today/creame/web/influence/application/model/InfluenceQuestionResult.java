package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.*;
import today.creame.web.share.support.SecurityContextSupporter;

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

    public InfluenceQuestionResult(InfluenceQna qna) {
        this.id = qna.getId();
        this.questionerId = qna.getQuestioner().getId();
        this.nickname = qna.getQuestioner().getNickname();
        this.influence = new InfluenceSimpleResult(qna.getInfluence());
        this.answered = qna.isAnswered();
        this.me = questionerId.equals(SecurityContextSupporter.orElseGetEmpty());
        this.question = Content.secretContent(qna.getQuestions(), me);
        this.answer = Content.secretContent(qna.getAnswers(), me);
    }
}