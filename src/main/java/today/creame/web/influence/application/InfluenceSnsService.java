package today.creame.web.influence.application;

import today.creame.web.influence.domain.SNS;

public interface InfluenceSnsService {

    SNS get(Long id);
    void update(Long id, SNS sns);
}
