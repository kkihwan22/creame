package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.SNS;
import today.creame.web.influence.exception.NotFoundInfluenceException;

@RequiredArgsConstructor
@Service
public class InfluenceSnsServiceImpl implements InfluenceSnsService {
    private final Logger log = LoggerFactory.getLogger(InfluenceSnsServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;

    @Override
    public SNS get(Long id) {
        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug(" find influence: {}", influence);

        return influence.getSns();
    }

    @Transactional
    @Override
    public void update(Long id, SNS sns) {
        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug(" find influence: {}", influence);
    }
}
