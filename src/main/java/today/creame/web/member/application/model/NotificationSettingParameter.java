package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.NotificationSettingCode;
import today.creame.web.share.aspect.permit.PermitRule;

@Getter
@ToString
public class NotificationSettingParameter {
    @PermitRule(type = ME)
    private Long id;
    private NotificationSettingCode code;
    private boolean condition;

    public NotificationSettingParameter(NotificationSettingCode code, boolean condition) {
        this.code = code;
        this.condition = condition;
    }
}
