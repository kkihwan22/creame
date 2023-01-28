package today.creame.web.influence.application.model;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.Content;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.influence.domain.QnaStatus;
import today.creame.web.member.domain.Member;

@Getter @ToString
public class InfluenceQnaResult {
    private Long id;
    private Long questionerId;
    private QnaStatus status;
    private boolean me;
    private Content question;
    private Content answer;

    public InfluenceQnaResult(Long reqId, InfluenceQna qna) {
        this.id = qna.getId();
        Member questioner = qna.getQuestioner();
        this.questionerId = questioner.getId();
        this.status = qna.getStatus();
        this.me = questioner.getId().equals(reqId);
        this.question = Content.secretContent(qna.getQuestions(), me);
        this.answer = Content.secretContent(qna.getAnswers(), me);
    }
}
