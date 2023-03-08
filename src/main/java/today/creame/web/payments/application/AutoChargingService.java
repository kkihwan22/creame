package today.creame.web.payments.application;

import today.creame.web.payments.application.model.AutoChargingParameter;

public interface AutoChargingService {

    void register(AutoChargingParameter parameter);

    void request();

    void remove();

    void callback();
}
