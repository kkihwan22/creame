package today.creame.web.m2net.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import today.creame.web.m2net.application.model.M2netUserCreateParameter;
import today.creame.web.m2net.application.model.M2netUserUpdateParameter;
import today.creame.web.m2net.exception.ConflictMemberException;
import today.creame.web.m2net.infra.feign.M2netUserClient;
import today.creame.web.m2net.infra.feign.io.M2netAutoChargingUpdateRequest;
import today.creame.web.m2net.infra.feign.io.M2netUserCreateRequest;
import today.creame.web.m2net.infra.feign.io.M2netUserCreateResponse;
import today.creame.web.share.event.AutoChargingConfigEvent;

@RequiredArgsConstructor
@Service
public class M2netUserServiceImpl implements M2netUserService {
    private final Logger log = LoggerFactory.getLogger(M2netUserServiceImpl.class);
    private final M2netUserClient client;

    @Override
    public String create(M2netUserCreateParameter parameter) {
        ResponseEntity<M2netUserCreateResponse> response = client.create(new M2netUserCreateRequest(parameter));
        log.info(" Counselor Created. >");
        log.info(" [ status ] {}", response.getStatusCode());
        log.info(" [ body ] {}", response.getBody());
        log.info(" Counselor Created. <");

        if (!response.getBody().getReqResult().equals("00")) {
            throw new ConflictMemberException();
        }

        return response.getBody().getMembid();
    }

    @Override
    public void updateAutoChargingConfig(AutoChargingConfigEvent event) {
        log.debug("auto charging config event: {}", event);
        client.updateAutoChargingConfig(event.getMid(), new M2netAutoChargingUpdateRequest(event));
    }

    @Override
    public void updateMemberInfo(String mid, M2netUserUpdateParameter parameter) {
        log.info("M2netUserServiceImpl.updateMemberInfo request >> {} {} {}", mid, parameter.getMembnm(), parameter.getTelno());
        ResponseEntity<M2netUserCreateResponse> response = client.update(mid, parameter);
        log.info("M2netUserServiceImpl.updateMemberInfo response >> {} {}", response.getStatusCode(), response.getBody().getResultmessage());

    }
}
