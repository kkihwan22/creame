package today.creame.web.m2net.application.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netCounselorCreateParameter {
    private Long influenceId;
    private String nickname;
    private String phoneNumber;
    private Integer priceUnitPerSecond;
    private Integer pricePerSecond;

    public M2netCounselorCreateParameter(Long influenceId, String nickname, String phoneNumber, Integer priceUnitPerSecond, Integer pricePerSecond) {
        this.influenceId = influenceId;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.priceUnitPerSecond = priceUnitPerSecond;
        this.pricePerSecond = pricePerSecond;
    }
}
