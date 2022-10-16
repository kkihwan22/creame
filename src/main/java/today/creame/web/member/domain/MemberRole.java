package today.creame.web.member.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.member.domain.converter.MemberRoleCodeConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "member_role")
@DynamicInsert
@DynamicUpdate
@Getter @ToString(exclude = {"member"})
public class MemberRole extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", insertable = false, updatable = false)
    private Member member;

    @Convert(converter = MemberRoleCodeConverter.class)
    @Column(name = "code_name")
    private MemberRoleCode codeName;

    public MemberRole(Long id, MemberRoleCode codeName) {
        this.id = id;
        this.codeName = codeName;
    }

    public void setMember(Member member) {
        if (this.member != null) {
            this.member.getRoles().remove(this);
        }

        this.member = member;

        if (!member.getRoles().contains(this)) {
            member.addRole(this);
        }
    }
}