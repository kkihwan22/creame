package today.creame.web.payments.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import today.creame.web.matching.exception.NotFoundMatchingException;
import today.creame.web.member.domain.Member;
import today.creame.web.member.domain.MemberJpaRepository;
import today.creame.web.payments.application.model.AutoChargingParameter;
import today.creame.web.share.support.SecurityContextSupporter;

@RequiredArgsConstructor
@Service
public class AutoChargingServiceImpl implements AutoChargingService {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    public void register(AutoChargingParameter parameter) {
        Long id = SecurityContextSupporter.getId();
        Member member = memberJpaRepository.findById(id).orElseThrow(NotFoundMatchingException::new);
    }

    @Override
    public void request() {

    }

    @Override
    public void remove() {

    }

    @Override
    public void callback() {

    }
}
