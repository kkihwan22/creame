package today.creame.web.m2net.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import today.creame.web.m2net.application.model.M2netCounselorCreateParameter;
import today.creame.web.m2net.infra.feign.M2netCounselorClient;
import today.creame.web.m2net.infra.feign.io.M2netCounselorCreateRequest;
import today.creame.web.m2net.infra.feign.io.M2netCounselorCreateResponse;
import today.creame.web.m2net.infra.feign.io.M2netCounselorStateRequest;

@RequiredArgsConstructor
@Service
public class M2NetCounselorServiceImpl implements M2netCounselorService {
    private final Logger log = LoggerFactory.getLogger(M2NetCounselorServiceImpl.class);
    private final M2netCounselorClient client;

    // TODO: ExternalServiceException 을 만들고 M2netException으로 처리되도록 작업

    @Override
    public String create(M2netCounselorCreateParameter parameter) {
        ResponseEntity<M2netCounselorCreateResponse> response = client.create(new M2netCounselorCreateRequest(parameter));
        log.info(" Counselor Created. >");
        log.info(" [ status ] {}", response.getStatusCode());
        log.info(" [ body ] {}", response.getBody());
        log.info(" Counselor Created. <");

        return response.getBody().getCsrid();
    }

    @Override
    public void on(String cid) {
        client.on(cid, M2netCounselorStateRequest.idle());
    }

    @Override
    public void off(String cid) {
        client.off(cid, M2netCounselorStateRequest.abse());
    }
}