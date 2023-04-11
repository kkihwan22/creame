package today.creame.web.influence.domain;

import static javax.persistence.GenerationType.IDENTITY;
import static today.creame.web.influence.domain.InfluenceApplicationStatus.REQUEST;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.converter.InfluenceApplicationStatusToStringConverter;
import today.creame.web.influence.exception.NotInApplicationStatusException;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "influence_application")
@DynamicInsert
@DynamicUpdate
@Getter @ToString
public class InfluenceApplication extends BaseCreatedAndUpdatedDateTime {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "introduction", columnDefinition = "TEXT")
    private String introduction;

    @Column(name = "categories")
    private String categories;

    @Column(name = "profile_images")
    private String profileImages;

    @Convert(converter = InfluenceApplicationStatusToStringConverter.class)
    @Column(name = "status")
    private InfluenceApplicationStatus status;

    public InfluenceApplication(Long id, String name, String nickname, String phoneNumber, String email, String introduction, String categories, String profileImages, InfluenceApplicationStatus status) {
        this.id = id;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.introduction = introduction;
        this.categories = categories;
        this.profileImages = profileImages;
        this.status = status;
    }

    public void approve() {
        if(!REQUEST.equals(this.status)) {
            throw new NotInApplicationStatusException();
        }
        this.status = InfluenceApplicationStatus.APPROVAL;
    }

    public void cancel() {
        this.status = InfluenceApplicationStatus.CANCEL;
    }

    public void reject() {
        if(!REQUEST.equals(this.status)) {
            throw new NotInApplicationStatusException();
        }
        this.status = InfluenceApplicationStatus.REJECT;
    }
}
