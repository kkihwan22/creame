package today.creame.web.member.application.model;

import static today.creame.web.share.aspect.permit.PermitRuleType.ME;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.member.domain.NotificationSettingItem;
import today.creame.web.share.aspect.permit.PermitRule;
import today.creame.web.share.model.BaseParameter;

@Getter
@ToString
public class NotificationSettingParameter implements BaseParameter {
    @PermitRule(type = ME)
    private Long id;
    private NotificationSettingItem item;
    private boolean condition;

    public NotificationSettingParameter(Long id, NotificationSettingItem item, boolean condition) {
        this.id = id;
        this.item = item;
        this.condition = condition;
    }

    @Override
    public Object toEntity() {
        return null;
    }
}
