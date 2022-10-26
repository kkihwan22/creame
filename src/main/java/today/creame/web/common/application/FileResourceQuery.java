package today.creame.web.common.application;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import today.creame.web.common.domain.FileResource;
import today.creame.web.common.domain.FileResourceJpaRepository;
import today.creame.web.influence.application.InfluenceProfileFileResourceQuery;
import today.creame.web.influence.application.model.InfluenceProfileImageFileResourceResult;

@RequiredArgsConstructor
@Component
public class FileResourceQuery implements InfluenceProfileFileResourceQuery {
    private final Logger log = LoggerFactory.getLogger(FileResourceQuery.class);
    private final FileResourceJpaRepository fileResourceJpaRepository;

    @Override
    public List<InfluenceProfileImageFileResourceResult> findInfluenceProfileImages(List<Long> ids) {
        List<FileResource> results = fileResourceJpaRepository.findFileResourceByIdIn(ids);
        log.debug("results: {}", results);

        AtomicInteger orderNumber = new AtomicInteger();
        return results.stream()
            .map(result ->
                new InfluenceProfileImageFileResourceResult(
                    result.getId(),
                    result.getObjectKey(),
                    result.getDeleted(),
                    orderNumber.getAndIncrement()))
            .collect(Collectors.toList());
    }
}
