package today.creame.web.member.domain;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.coin.domain.CoinsHistoryType;
import today.creame.web.m2net.application.M2netUserService;
import today.creame.web.m2net.application.model.M2netUserCreateParameter;
import today.creame.web.member.domain.converter.MemberStatusConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "member")
@DynamicInsert
@DynamicUpdate
@Getter
@ToString(exclude = {
    "roles"
})
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

    @OneToMany(mappedBy = "member", fetch = LAZY, cascade = ALL)
    private List<MemberNotificationPreference> notificationPreferences = new ArrayList<>();

    @Column(name = "coins")
    private int coins;

    @Column(name = "bonus_coins")
    private int bonusCoins;

    @Enumerated(EnumType.STRING)
    @Column(name = "signup_type")
    private SignupType signupType;

    @OneToMany(mappedBy = "member")
    private List<MemberRole> roles = new ArrayList<>();

    public Member(String email, String password, String nickname, String phoneNumber, MemberStatus status, SignupType signupType) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.signupType = signupType;
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

    public void registerM2netMember(M2netUserService m2netUserService) {
        m2netUserId = m2netUserService.create(new M2netUserCreateParameter(nickname, phoneNumber));
    }

    public void updateCoins(CoinsHistoryType type, int coins) {
        switch (type) {
            case USING:
                this.coins = this.coins - coins;
                break;
            case CHARGING:
                this.coins = this.coins + coins;
                break;
            case CANCELED:
                this.coins = this.coins + -(Math.abs(coins));
                break;
            case REWARD:
                this.bonusCoins = this.bonusCoins + coins;
                this.coins = this.coins + this.coins;
                break;
        }
    }
}