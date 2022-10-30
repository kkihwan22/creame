package today.creame.web.influence.application;

import today.creame.web.share.domain.OnOffStatus;

public interface InfluenceConnectedService {

    void patchConnectionStatus(Long id, OnOffStatus status);
}
