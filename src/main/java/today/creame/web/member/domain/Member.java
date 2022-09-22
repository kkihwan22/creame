package today.creame.web.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.member.domain.converter.MemberStatusConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

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
    private List<MemberRole> roles;

    public Member(Long id, String email, String password, String nickname, String phoneNumber, MemberStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public void addRole(MemberRole role) {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        roles.add(role);
        role.setMember(this);
    }

    public void removeRole(MemberRole role) {
        roles.remove(role);
        role.setMember(null);
    }
}