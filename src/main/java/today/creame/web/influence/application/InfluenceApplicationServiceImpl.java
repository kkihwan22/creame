package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.delegate.MemberCreateDelegate;
import today.creame.web.influence.application.model.InfluenceApplicationParameter;
import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.domain.InfluenceApplicationJpaRepository;
import today.creame.web.influence.exception.NotFoundApplicationException;

@RequiredArgsConstructor
@Service
public class InfluenceApplicationServiceImpl implements InfluenceApplicationService {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationServiceImpl.class);
    private final MemberCreateDelegate memberCreateDelegate;
    private final InfluenceService influenceService;
    private final InfluenceApplicationJpaRepository influenceApplicationJpaRepository;

    @Transactional
    @Override
    public Long register(InfluenceApplicationParameter parameter) {
        log.debug("parameter: {}", parameter);
        InfluenceApplication influenceApplication = parameter.toEntity();
        influenceApplicationJpaRepository.save(influenceApplication);
        log.debug("application id: {}", influenceApplication.getId());
        return influenceApplication.getId();
    }

    @Transactional
    @Override
    public void approve(Long id) {
        log.debug("approve application id: {}", id);
        InfluenceApplication application = influenceApplicationJpaRepository.findById(id)
            .orElseThrow(NotFoundApplicationException::new);
        log.debug("find application: {}", application);

        Long memberId = memberCreateDelegate.createMember(application);
        Long influenceId = influenceService.create(new InfluenceCreateParameter(memberId, application));

        log.debug("member:{}, influence:{}", memberId, influenceId);
        application.approve();
    }

    @Transactional
    @Override
    public void cancel(Long id) {
        log.debug("cancel application id: {}", id);
        InfluenceApplication application = influenceApplicationJpaRepository.findById(id)
            .orElseThrow(NotFoundApplicationException::new);
        log.debug("find application: {}", application);
        application.cancel();
    }

    @Transactional
    @Override
    public void reject(Long id) {
        log.debug("reject application id: {}", id);
        InfluenceApplication application = influenceApplicationJpaRepository.findById(id)
            .orElseThrow(NotFoundApplicationException::new);
        log.debug("find application: {}", application);
        application.reject();
    }
}
