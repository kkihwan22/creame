package today.creame.web.member.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Entity
@Table(name = "member_notification_preference")
@Getter
@ToString(exclude = {"member"})
public class MemberNotificationPreference {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @Column(name = "sms")
    private boolean sms;

    @Column(name = "email")
    private boolean email;

    @Column(name = "event")
    private boolean event;

    @Column(name = "answer")
    private boolean answer;

    @Column(name = "review")
    private boolean review;

    @Column(name = "recent_influence")
    private boolean recentInfluence;

    public MemberNotificationPreference(Member member) {
        this.member = member;
        member.getNotificationPreferences().add(this);
    }

    public void changeNotificationCondition(NotificationSettingItem item, boolean condition) {
        switch (item) {
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
        }
    }
}
