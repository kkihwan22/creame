package today.creame.web.influence.application;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import today.creame.web.influence.application.model.InfluenceBookmarkParameter;
import today.creame.web.influence.domain.InfluenceBookmark;
import today.creame.web.influence.domain.InfluenceBookmarkJpaRepository;

@RequiredArgsConstructor
@Service
public class InfluenceBookmarkServiceImpl implements InfluenceBookmarkService {
    private final Logger log = LoggerFactory.getLogger(InfluenceBookmarkServiceImpl.class);
    private final InfluenceBookmarkJpaRepository influenceBookmarkJpaRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean isBookmarked(InfluenceBookmarkParameter parameter) {
        Long count = influenceBookmarkJpaRepository.countByMemberIdAndInfluenceIdAndBookmarked(parameter.getMyId(), parameter.getInfluenceId(), true);
        log.debug("bookmarked: {}", count);
        return count > 0L;
    }

    @Transactional
    @Override
    public void bookmarked(InfluenceBookmarkParameter parameter) {
        InfluenceBookmark bookmark = influenceBookmarkJpaRepository
            .findByMemberIdAndInfluenceId(parameter.getMyId(), parameter.getInfluenceId())
            .orElse(parameter.toEntity());
        bookmark.marked();
        log.debug("bookmark: {}", bookmark);
        influenceBookmarkJpaRepository.save(bookmark);
    }

    @Transactional
    @Override
    public void canceledBookmark(InfluenceBookmarkParameter parameter) {
        InfluenceBookmark bookmark = influenceBookmarkJpaRepository
            .findByMemberIdAndInfluenceId(parameter.getMyId(), parameter.getInfluenceId())
            .orElse(parameter.toEntity());
        bookmark.canceled();
        influenceBookmarkJpaRepository.save(bookmark);
    }
}
