package today.creame.web.member.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Convert;
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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.member.domain.converter.MemberRoleCodeConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

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
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @Convert(converter = MemberRoleCodeConverter.class)
    @Column(name = "code_name")
    private MemberRoleCode codeName;

    public MemberRole(Long id, MemberRoleCode codeName) {
        this.id = id;
        this.codeName = codeName;
    }
}