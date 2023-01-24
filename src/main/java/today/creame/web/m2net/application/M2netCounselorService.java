package today.creame.web.m2net.application;

import today.creame.web.m2net.application.model.M2netCounselorCreateParameter;

public interface M2netCounselorService {

    String create(M2netCounselorCreateParameter parameter);

    void on(String cid);

    void off(String cid);
}
