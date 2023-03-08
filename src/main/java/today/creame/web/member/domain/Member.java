package today.creame.web.member.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.m2net.application.M2netUserService;
import today.creame.web.m2net.application.model.M2netUserCreateParameter;
import today.creame.web.member.domain.converter.MemberStatusConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "member")
@DynamicInsert
@DynamicUpdate
@Getter @ToString(exclude = "roles")
public class Member extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "m2net_uid")
    private String m2netUserId;

    @Convert(converter = MemberStatusConverter.class)
    @Column(name = "status")
    private MemberStatus status;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberNotificationPreference> notificationPreferences = new ArrayList<>();

    //TODO: 테이블로 분리
    @Embedded
    private NotificationSetting notificationSetting;

//    @ManyToOne
//    @JoinColumn(name = "auto_charging_id")
//    private AutoCharging autoCharging;

    @Column(name = "auto_charging_id")
    private Long autoChargingId;

    @Column(name = "coins")
    private int coins;

    @Column(name = "benefit_coins")
    private int benefitCoins;

    @OneToMany(mappedBy = "member")
    private List<MemberRole> roles = new ArrayList<>();

    public Member(String email, String password, String nickname, String phoneNumber, MemberStatus status) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Member(Long id) {
        this.id = id;
    }

    public void addRole(MemberRole role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(role);
        role.setMember(this);
    }

    public void changedNickname(String nickname) {
        this.nickname = nickname;
    }

    public void changedPassword(String password) {
        this.password = password;
    }

    public void updateNotificationSetting(NotificationSettingItem code, boolean condition) {
        this.notificationSetting = new NotificationSetting(this.notificationSetting, code, condition);
    }

    public void registerM2netMember(M2netUserService m2netUserService) {
        m2netUserId = m2netUserService.create(new M2netUserCreateParameter(nickname, phoneNumber));
    }
}