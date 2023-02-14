package today.creame.web.m2net.application;

import today.creame.web.m2net.application.model.M2netNoticeCommand;

public interface M2netService {

    void preCall(Long influenceId, Long memberId);

    void postNotice(M2netNoticeCommand command);
}
