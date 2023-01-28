package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaQuestionParameter;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.influence.domain.InfluenceQnaJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.influence.exception.NotFoundQnAException;

@RequiredArgsConstructor
@Service
public class InfluenceQnaServiceImpl implements InfluenceQnaService {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceQnaJpaRepository influenceQnaJpaRepository;

    @Transactional
    @Override
    public void ask(InfluenceQnaQuestionParameter parameter) {
        log.debug("parameter: {}", parameter);

        Influence influence = influenceJpaRepository.findById(parameter.getInfluenceId())
            .orElseThrow(NotFoundInfluenceException::new);

        influence.addQuestion();

        log.debug("find influence: {}", influence);

        InfluenceQna influenceQna = parameter.toEntity();
        influenceQnaJpaRepository.save(influenceQna);
    }

    @Transactional
    @Override
    public void answer(InfluenceQnaAnswerParameter parameter) {
        log.debug("parameter: {}", parameter);

        InfluenceQna qna = influenceQnaJpaRepository.findById(parameter.getQnaId()).orElseThrow(NotFoundQnAException::new);
        log.debug("find qna: {}", qna);
        qna.answer(parameter.getContent());
    }
}
