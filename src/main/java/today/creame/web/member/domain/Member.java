package today.creame.web.member.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Convert;
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

    @Convert(converter = MemberStatusConverter.class)
    @Column(name = "status")
    private MemberStatus status;

    @OneToMany(mappedBy = "member")
    private List<MemberRole> roles = new ArrayList<>();

    public Member(Long id, String email, String password, String nickname, String phoneNumber, MemberStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public Member(Long id) {
        this.id = id;
        // this(id, null, null, null, null, null);
    }

    public void addRole(MemberRole role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }

        roles.add(role);
        if (role.getMember() != this) {
            role.setMember(this);
        }
    }

    public void removeRole(MemberRole role) {
        roles.remove(role);
        role.setMember(null);
    }
}