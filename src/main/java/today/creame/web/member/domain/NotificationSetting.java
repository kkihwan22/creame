package today.creame.web.member.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = PROTECTED)
@Embeddable
@Getter
@ToString
public class NotificationSetting {
    @Column(
        name = "sms_notification_setting"
    )
    private boolean sms;
    @Column(
        name = "email_notification_setting"
    )
    private boolean email;
    @Column(
        name = "event_notification_setting"
    )
    private boolean event;
    @Column(
        name = "answer_notification_setting"
    )
    private boolean answer;
    @Column(
        name = "review_notification_setting"
    )
    private boolean review;
    @Column(
        name = "recent_influence_notification_setting"
    )
    private boolean recentInfluence;

    public NotificationSetting(NotificationSetting current, NotificationSettingItem code, boolean condition) {
        this.sms = current.sms;
        this.email = current.email;
        this.event = current.event;
        this.answer = current.answer;
        this.review = current.review;
        this.recentInfluence = current.recentInfluence;

        switch (code) {
            case SMS:
                this.sms = condition;
                break;
            case EMAIL:
                this.email = condition;
                break;
            case EVENT:
                this.event = condition;
                break;
            case ANSWER:
                this.answer = condition;
                break;
            case REVIEW:
                this.review = condition;
                break;
            case RECENT_INFLUENCE:
                this.recentInfluence = condition;
                break;
            default:
                throw new IllegalArgumentException("Not matched 'Notification Setting Code'.");
        }
    }
}
