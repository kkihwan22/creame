package today.creame.web.influence.application;

import today.creame.web.share.domain.OnOffCondition;

public interface InfluenceConnectedService {

    void patchConnectionStatus(Long id, OnOffCondition status);
}
