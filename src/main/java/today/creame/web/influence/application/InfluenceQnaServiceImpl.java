package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceQnaAnswerParameter;
import today.creame.web.influence.application.model.InfluenceQnaAskParameter;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.influence.domain.InfluenceQnaJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;

@RequiredArgsConstructor
@Service
public class InfluenceQnaServiceImpl implements InfluenceQnaService {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceQnaJpaRepository influenceQnaJpaRepository;

    @Transactional
    @Override
    public void ask(InfluenceQnaAskParameter parameter) {
        Influence influence = influenceJpaRepository.findById(parameter.getInfluenceId())
            .orElseThrow(NotFoundInfluenceException::new);

        influence.addQuestion();

        log.debug("find influence: {}", influence);

        InfluenceQna influenceQna = parameter.toEntity();
        influenceQnaJpaRepository.save(influenceQna);
    }

    @Override
    public void answer(InfluenceQnaAnswerParameter parameter) {

    }
}
