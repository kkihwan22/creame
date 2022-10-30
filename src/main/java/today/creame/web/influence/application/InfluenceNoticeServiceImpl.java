package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.NotFoundInfluenceException;

@RequiredArgsConstructor
@Service
public class InfluenceNoticeServiceImpl implements InfluenceNoticeService {
    private final Logger log = LoggerFactory.getLogger(InfluenceSnsServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;

    @Override
    public String get(Long id) {
        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug(" find influence:{}", influence);

        return influence.getNotice();
    }

    @Override
    public void update(Long id, String notice) {
        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug(" find influence:{}", influence);

        influence.updateNotice(notice);
    }
}
