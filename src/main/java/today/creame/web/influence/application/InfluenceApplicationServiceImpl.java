package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceApplicationParameter;
import today.creame.web.influence.application.model.InfluenceCreateParameter;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.domain.InfluenceApplicationJpaRepository;
import today.creame.web.influence.exception.NotFoundApplicationException;
import today.creame.web.member.application.MemberService;
import today.creame.web.member.application.model.MemberRegisterParameter;
import today.creame.web.member.application.model.MemberResult;
import today.creame.web.member.domain.SignupType;
import today.creame.web.share.event.SmsSendEvent;
import today.creame.web.share.support.RandomString;

@RequiredArgsConstructor
@Service
public class InfluenceApplicationServiceImpl implements InfluenceApplicationService {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationServiceImpl.class);
    private final MemberService memberService;
    private final InfluenceService influenceService;
    private final InfluenceApplicationJpaRepository influenceApplicationJpaRepository;
    private final ApplicationEventPublisher publisher;

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

        application.approve();

        String password = RandomString.password().nextString();
        log.debug(" [ password]: {}", password);
        MemberResult member = memberService.registerMemberInfluence(
            new MemberRegisterParameter(application.getEmail(), application.getNickname(), password, application.getPhoneNumber(), null, SignupType.EMAIL));

        Long influenceId = influenceService.create(new InfluenceCreateParameter(member.getId(), application));
        log.debug("member:{}, influence:{}", member.getId(), influenceId);

        publisher.publishEvent(new SmsSendEvent(member.getPhoneNumber(), member.getPassword()));
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
