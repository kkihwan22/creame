package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.domain.CounselorStatus;

@Getter
@ToString
public class M2netCounselorStateRequest {

    private String state;

    private M2netCounselorStateRequest(CounselorStatus status) {
        this.state = status.name();
    }

    public static M2netCounselorStateRequest idle() {
        return new M2netCounselorStateRequest(CounselorStatus.IDLE);
    }

    public static M2netCounselorStateRequest abse() {
        return new M2netCounselorStateRequest(CounselorStatus.ABSE);
    }

    public static M2netCounselorStateRequest conn() {
        return new M2netCounselorStateRequest(CounselorStatus.CONN);
    }
}