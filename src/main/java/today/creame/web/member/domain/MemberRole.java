package today.creame.web.member.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.member.domain.converter.MemberRoleConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@NoArgsConstructor
@Entity
@Table(name = "member_role")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class MemberRole extends BaseCreatedAndUpdatedDateTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    @Setter
    private Member member;

    @Convert(converter = MemberRoleConverter.class)
    @Column(name = "code_name")
    private MemberRoleCode codeName;

    public MemberRole(Long id, MemberRoleCode codeName) {
        this.id = id;
        this.codeName = codeName;
    }
}