package today.creame.web.m2net.application;

import today.creame.web.m2net.infra.feign.io.M2netCounselorInfoUpdateRequest;

public interface M2netCounselorService {
    void on(String cid);
    void off(String cid);
    void updateInfluenceInfo(String cid, M2netCounselorInfoUpdateRequest parameter);
}
