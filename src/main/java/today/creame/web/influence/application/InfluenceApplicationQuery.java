package today.creame.web.influence.application;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceApplicationResult;
import today.creame.web.influence.domain.InfluenceApplication;
import today.creame.web.influence.domain.InfluenceApplicationJpaRepository;
import today.creame.web.influence.domain.InfluenceApplicationStatus;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class InfluenceApplicationQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceApplicationQuery.class);
    private final InfluenceApplicationJpaRepository influenceApplicationJpaRepository;

    public List<InfluenceApplicationResult> list(List<InfluenceApplicationStatus> influenceApplicationStatuses) {
        log.debug("in status: {}", influenceApplicationStatuses);
        List<InfluenceApplication> applications = influenceApplicationJpaRepository.findInfluenceApplicationByStatusIn(influenceApplicationStatuses);
        log.debug("applications: {}", applications);

        return applications.stream()
            .map(application -> new InfluenceApplicationResult(application))
            .collect(Collectors.toList());
    }
}