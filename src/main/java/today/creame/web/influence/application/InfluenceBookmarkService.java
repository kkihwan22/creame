package today.creame.web.influence.application;

import today.creame.web.influence.application.model.InfluenceBookmarkParameter;

public interface InfluenceBookmarkService {

    boolean isBookmarked(InfluenceBookmarkParameter parameter);
    void bookmarked(InfluenceBookmarkParameter parameter);
    void canceledBookmark(InfluenceBookmarkParameter parameter);
}
