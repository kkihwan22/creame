package today.creame.web.m2net.application;

import today.creame.web.m2net.application.model.M2netUpdateCallStatusCommand;

public interface M2netService {

    void preCall(Long influenceId, Long memberId);

    void updateCallStatus(M2netUpdateCallStatusCommand command);
}
