package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.domain.CounselorCondition;

@Getter
@ToString
public class M2netCounselorStateRequest {

    private String state;

    private M2netCounselorStateRequest(CounselorCondition status) {
        this.state = status.name();
    }

    public static M2netCounselorStateRequest idle() {
        return new M2netCounselorStateRequest(CounselorCondition.IDLE);
    }

    public static M2netCounselorStateRequest abse() {
        return new M2netCounselorStateRequest(CounselorCondition.ABSE);
    }

    public static M2netCounselorStateRequest conn() {
        return new M2netCounselorStateRequest(CounselorCondition.CONN);
    }
}