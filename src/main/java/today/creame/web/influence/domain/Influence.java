package today.creame.web.influence.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import today.creame.web.influence.domain.converter.InfluenceRankToStringConverter;
import today.creame.web.share.domain.BaseCreatedAndUpdatedDateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "calling")
    private boolean calling;

    @Column(name = "blocked")
    private boolean blocked;

    @Column(name = "enabled")
    private boolean exposed;

    @Column(name = "certified")
    private boolean certified;

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

    @Column(name = "item")
    private Long item;

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
    private int rate;

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

    @OneToMany(mappedBy = "influence", cascade = CascadeType.ALL)
    private List<InfluenceCategory> categories = new ArrayList<>();

    @OneToMany(mappedBy = "influence", cascade = CascadeType.ALL)
    private List<InfluenceProfileImage> profileImages;

    public Influence(
        Long memberId,
        String extensionNumber,
        String name,
        String nickname,
        String phoneNumber,
        String email,
        String introduction
    ) {
        this.id = memberId;
        this.extensionNumber = extensionNumber;
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.introduction = introduction;

        this.rank = Rank.WHITE;
        this.item = 51L;
        this.exposed = true;
        this.connected = true;
        this.certified = true;
    }

    public Influence(Long id) {
        this.id = id;
    }

    public void updateCategory(InfluenceCategory category) {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        categories.add(category);
        category.setInfluence(this);
    }

    public void updateInfluenceProfileImage(InfluenceProfileImage profileImage) {
        if (profileImages == null) {
            profileImages = new ArrayList<>();
        }

        profileImages.add(profileImage);
        profileImage.setInfluence(this);
    }

    public void updateConnect() {
        this.connected = !(this.connected);
    }

    public void startCall() {
        this.calling = true;
    }

    public void endCall() {
        this.calling = false;
    }

    public void updateSns(SNS sns) {
        this.sns = sns;
    }

    public void updateNotice(String notice) {
        this.notice = notice;
    }

    public void addQuestion() {
        this.qnaCount = this.getQnaCount() + 1;
        this.qnaNotAnswerCount = this.getQnaNotAnswerCount() + 1;
    }

    public void answer() {
        this.qnaAnswerCount = this.qnaAnswerCount + 1;
        this.qnaNotAnswerCount = this.qnaNotAnswerCount - 1;
    }

    public void putGreetings(Long id, String uri) {
        this.greetings = new Greetings(id, uri);
    }

    public void changeItem(Long item) {
        this.item = item;
    }

    public void updateCid(String cid) {
        this.m2NetCounselorId = cid;
    }

    public void registerReview(int rate) {
        this.rate = this.rate + rate;
        this.rateCount = this.rateCount + 1;
        this.reviewCount = this.reviewCount + 1;
        this.reviewNotAnswerCount = this.reviewNotAnswerCount + 1;
    }

    public void answeredReview() {
        this.reviewNotAnswerCount = this.reviewNotAnswerCount -1;
        this.reviewAnswerCount = this.reviewAnswerCount + 1;
    }

    public float avgRate() {
        try {
            return this.rate / this.rateCount;
        } catch (ArithmeticException e) {
            return 0;
        }
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
//            return false;
//        Influence i = (Influence) o;
//        return Objects.equals(id, i.getId());
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id != null ? id.hashCode() : 0;
//        return result;
//    }


    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void blocked() {
        this.blocked = true;
    }

    public void changedExposeStatus(boolean status) {
        this.exposed = status;
    }

    public void updateInfo(String name, Rank rank, boolean exposed, String introduction) {
        this.name = name;
        this.rank = rank;
        this.exposed = exposed;
        this.introduction = introduction;
    }
}