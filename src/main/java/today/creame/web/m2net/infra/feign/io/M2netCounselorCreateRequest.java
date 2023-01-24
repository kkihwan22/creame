package today.creame.web.m2net.infra.feign.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.m2net.application.model.CounselorBillingMethod;
import today.creame.web.m2net.application.model.CounselorStatus;
import today.creame.web.m2net.application.model.M2netCounselorCreateParameter;

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

    public M2netCounselorCreateRequest(M2netCounselorCreateParameter parameter) {
        this.csrnm = parameter.getNickname();
        this.dtmfno = parameter.getInfluenceId().toString();
        this.telno = parameter.getPhoneNumber();
        this.dectm = parameter.getPriceUnitPerSecond();
        this.decamt = parameter.getPricePerSecond();
        this.state = CounselorStatus.IDLE.name();
        this.sortno = 1;
        this.preflag = CounselorBillingMethod.ALL.getCode();
    }
}