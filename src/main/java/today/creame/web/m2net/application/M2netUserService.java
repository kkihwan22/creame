package today.creame.web.m2net.application;

import today.creame.web.m2net.application.model.M2netUserCreateParameter;
import today.creame.web.share.event.AutoChargingConfigEvent;

public interface M2netUserService {

    String create(M2netUserCreateParameter parameter);

    void updateAutoChargingConfig(AutoChargingConfigEvent event);
}
