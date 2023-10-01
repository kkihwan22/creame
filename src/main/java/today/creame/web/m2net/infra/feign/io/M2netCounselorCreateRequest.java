package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.domain.CounselorCondition;
import today.creame.web.m2net.domain.DeductionMethod;

@Getter
@ToString
public class M2netCounselorCreateRequest {
    private String csrnm;
    private String state;
    private int sortno;
    private String dtmfno;
    private String telno;
    private int dectm;
    private int decamt;
    private String preflag;

    public M2netCounselorCreateRequest(String csrnm, String dtmfno, String telno, int dectm, int decamt) {
        this.csrnm = csrnm;
        this.dtmfno = dtmfno;
        this.telno = telno;
        this.dectm = dectm / 10;
        this.decamt = decamt / 10;
        this.state = CounselorCondition.IDLE.name();
        this.sortno = 1;
        this.preflag = DeductionMethod.ALL.getCode();
    }
}