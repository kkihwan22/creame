package today.creame.web.influence.application;

public interface InfluenceNoticeService {

    String get(Long id);
    void update(Long id, String notice);
}
