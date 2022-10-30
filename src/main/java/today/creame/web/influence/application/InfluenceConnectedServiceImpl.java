package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.domain.Influence;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.exception.ConflictConnectionStatusException;
import today.creame.web.influence.exception.NotFoundInfluenceException;
import today.creame.web.share.domain.OnOffStatus;

@RequiredArgsConstructor
@Service
public class InfluenceConnectedServiceImpl implements InfluenceConnectedService {
    private final Logger log = LoggerFactory.getLogger(InfluenceConnectedServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;

    @Transactional
    @Override
    public void patchConnectionStatus(Long id, OnOffStatus status) {

        Influence influence = influenceJpaRepository.findById(id)
            .orElseThrow(NotFoundInfluenceException::new);
        log.debug("find influence:{}", influence);

        if (status == OnOffStatus.ON && influence.isConnected()) {
            throw new ConflictConnectionStatusException();
        }

        if (status == OnOffStatus.OFF && !influence.isConnected()) {
            throw new ConflictConnectionStatusException();
        }

        influence.updateConnect();
    }
}
