package today.creame.web.influence.domain;

import static javax.persistence.FetchType.LAZY;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.converter.InfluenceRankToStringConverter;
import today.creame.web.influence.domain.converter.ItemToIndexConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

@NoArgsConstructor
@Entity
@Table(name = "influence")
@DynamicInsert
@DynamicUpdate
@Getter @ToString(exclude = {
    "categories", "profileImages"
})
public class Influence extends BaseCreatedAndUpdatedDateTime {

    @Id
    private Long id;

    @Column(name = "extension_number")
    private String extensionNumber;

    @Column(name = "influence_name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(
        name = "phone_number",
        columnDefinition = "char(11)"
    )
    private String phoneNumber;

    @Convert(converter = InfluenceRankToStringConverter.class)
    @Column(name = "influence_rank")
    private Rank rank;

    // todo: connected history 테이블 필요
    @Column(name = "connected")
    private boolean connected;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(
        name = "introduction",
        columnDefinition = "text"
    )
    private String introduction;

    @AttributeOverrides({
        @AttributeOverride(name = "fileResourceId", column = @Column(name = "greetings_file_id")),
        @AttributeOverride(name = "fileResourceUri", column = @Column(name = "greetings_file_uri")),
    })
    @Embedded
    private Greetings greetings;

    @Convert(converter = ItemToIndexConverter.class)
    @Column(name = "item")
    private Item item;

    @Column(
        name = "notice",
        columnDefinition = "text"
    )
    private String notice;

    @AttributeOverrides({
        @AttributeOverride(name = "company", column = @Column(name = "sns_company")),
        @AttributeOverride(name = "address", column = @Column(name = "sns_address")),
    })
    @Embedded
    private SNS sns;

    @Column(name = "bookmark_cnt")
    private int bookmarkCount;

    @Column(name = "rate")
    private float rate;

    @Column(name = "rate_cnt")
    private int rateCount;

    @Column(name = "review_cnt")
    private int reviewCount;

    @Column(name = "review_answer_cnt")
    private int reviewAnswerCount;

    @Column(name = "review_not_answer_cnt")
    private int reviewNotAnswerCount;

    @Column(name = "qna_cnt")
    private int qnaCount;

    @Column(name = "qna_answer_cnt")
    private int qnaAnswerCount;

    @Column(name = "qna_not_answer_cnt")
    private int qnaNotAnswerCount;

    @Column(name = "m2net_cid")
    private String m2NetCounselorId;

    @OneToMany(fetch = LAZY)
    @JoinColumn(name = "influenceId")
    private List<InfluenceCategory> categories;

    @OneToMany(mappedBy = "influence", fetch = LAZY)
    private List<InfluenceProfileImage> profileImages;

    public Influence(
        Long memberId,
        String name,
        String nickname,
        String phoneNumber,
        String email,
        String introduction
    ) {
        this.id = memberId;
        this.extensionNumber = String.valueOf(memberId); // todo: API 연동 시 로직 확인
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;

        this.rank = Rank.WHITE;
        this.connected = false;
        this.item = ItemMap.get(1);
    }

    public Influence(Long id) {
        this.id = id;
    }

    public void addInfluenceCategory(InfluenceCategory category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
    }

    public void addInfluenceProfileImage(InfluenceProfileImage profileImage) {
        if (profileImages == null) {
            profileImages = new ArrayList<>();
        }

        profileImages.add(profileImage);
        if (profileImage.getInfluence() != this) {
            profileImage.addInfluence(this);
        }
    }

    public void removeInfluenceProfileImage(InfluenceProfileImage profileImage) {
        profileImages.remove(profileImage);
        profileImage.addInfluence(null);
    }

    public void updateConnect() {
        this.connected = !(this.connected);
    }

    public void updateSns(SNS sns) {
        this.sns = sns;
    }

    public void updateNotice(String notice) {
        this.notice = notice;
    }

    public void addQuestion() {
        this.qnaCount = this.getQnaCount() + 1;
        this.qnaNotAnswerCount = this.getReviewNotAnswerCount() + 1;
    }

    public void answer() {
        this.qnaAnswerCount = this.qnaAnswerCount + 1;
        this.qnaNotAnswerCount = this.qnaNotAnswerCount - 1;
    }

    public void putGreetings(Long id, String uri) {
        this.greetings = new Greetings(id, uri);
    }

    public void changeItem(Integer index) {
        this.item = ItemMap.get(index);
    }

    public void updateCid(String cid) {
        this.m2NetCounselorId = cid;
    }
}