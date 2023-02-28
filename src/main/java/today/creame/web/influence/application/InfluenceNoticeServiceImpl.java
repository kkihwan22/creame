package today.creame.web.influence.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceNoticeParameter;
import today.creame.web.influence.application.model.InfluenceNoticeResult;
import today.creame.web.influence.domain.InfluenceJpaRepository;
import today.creame.web.influence.domain.InfluenceNotice;
import today.creame.web.influence.domain.InfluenceNoticeJpaRepository;
import today.creame.web.share.aspect.permit.Permit;

@RequiredArgsConstructor
@Service
public class InfluenceNoticeServiceImpl implements InfluenceNoticeService {
    private final Logger log = LoggerFactory.getLogger(InfluenceNoticeServiceImpl.class);
    private final InfluenceJpaRepository influenceJpaRepository;
    private final InfluenceNoticeJpaRepository influenceNoticeJpaRepository;

    @Override
    public InfluenceNoticeResult get(Long id) {
        List<InfluenceNotice> results = influenceNoticeJpaRepository.findInfluenceNoticesByInfluenceIdAndDeleted(id, false);
        log.debug("results: {}", results);

        return results.isEmpty()
            ? null
            : new InfluenceNoticeResult(results.get(0));
    }

    @Transactional
    @Permit
    @Override
    public void update(InfluenceNoticeParameter parameter) {
        InfluenceNotice influenceNotice = parameter.toEntity();
        influenceNoticeJpaRepository.save(influenceNotice);
        log.debug("saved influence notice: {}", influenceNotice);

        parameter.getAttachFiles().forEach(it -> influenceNotice.attached(it));
    }
}
