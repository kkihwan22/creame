package today.creame.web.m2net.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import today.creame.web.m2net.infra.feign.M2netCounselorClient;
import today.creame.web.m2net.infra.feign.io.M2netCounselorStateRequest;

@RequiredArgsConstructor
@Service
public class M2NetCounselorServiceImpl implements M2netCounselorService {
    private final Logger log = LoggerFactory.getLogger(M2NetCounselorServiceImpl.class);
    private final M2netCounselorClient client;

    @Override
    public void on(String cid) {
        client.on(cid, M2netCounselorStateRequest.idle());
    }

    @Override
    public void off(String cid) {
        client.off(cid, M2netCounselorStateRequest.abse());
    }
}