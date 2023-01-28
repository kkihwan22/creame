package today.creame.web.m2net.application;

public interface M2netService {

    void preCall(Long influenceId, Long memberId);

    void postNotice();
}
