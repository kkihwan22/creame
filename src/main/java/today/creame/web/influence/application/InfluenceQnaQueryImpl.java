package today.creame.web.influence.application;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import today.creame.web.config.security.CustomUserDetails;
import today.creame.web.influence.application.model.InfluenceQnaAskParameter;
import today.creame.web.influence.application.model.InfluenceQnaQueryParameter;
import today.creame.web.influence.application.model.InfluenceQnaResult;
import today.creame.web.influence.domain.InfluenceQna;
import today.creame.web.influence.domain.InfluenceQnaJpaRepository;

@RequiredArgsConstructor
@Service
public class InfluenceQnaQueryImpl implements InfluenceQnaQuery {
    private final Logger log = LoggerFactory.getLogger(InfluenceQnaQueryImpl.class);
    private final InfluenceQnaJpaRepository influenceQnaJpaRepository;

    @Override
    public List<InfluenceQnaResult> listByPaging(InfluenceQnaQueryParameter parameter) {
        log.debug("parameter: {}", parameter);

        Page<InfluenceQna> results = parameter.me
            ? influenceQnaJpaRepository.findByQuestioner_Id(parameter.requesterId, parameter.pageable)
            : influenceQnaJpaRepository.findAll(parameter.pageable);

        return results.getContent().stream().map(result -> new InfluenceQnaResult(parameter.requesterId, result)).collect(Collectors.toList());
    }
}
