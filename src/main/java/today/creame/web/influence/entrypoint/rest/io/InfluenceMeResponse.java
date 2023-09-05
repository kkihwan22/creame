package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.application.model.InfluenceResult;
import today.creame.web.matching.applicaton.model.MatchingStatisticsResult;

import java.util.List;

@Getter @ToString
public class InfluenceMeResponse {

    private Long id;
    private String extensionNumber;
    private String nickname;
    private String snsCompany;
    private String snsAddress;
    private List<String> categories;
    private List<String> profileImages;
    private Long item;
    private String introduction;
    private String greetings;
    private boolean connected;
    private boolean blocked;
    private boolean exposed;
    private Integer monthlyMatchTime = 0;
    private Long monthlyMatchAmount = 0L;

    private int reviewCount;
    private int reviewAnswerCount;
    private int qnaCount;
    private int answerCount;

    public InfluenceMeResponse(InfluenceResult result, List<MatchingStatisticsResult> list) {
        this.id = result.getId();
        this.extensionNumber = result.getExtensionNumber();
        this.nickname = result.getNickname();
        this.snsCompany = result.getSnsCompany();
        this.snsAddress = result.getSnsAddress();
        this.categories = result.getCategories();
        this.profileImages = result.getProfileImages();
        this.item = result.getItem();
        this.introduction = result.getIntroduction();
        this.greetings = result.getGreetings();
        this.connected = result.isConnected();
        this.blocked = result.isBlocked();
        this.exposed = result.isExposed();
        this.reviewCount = result.getReviewCount();
        this.reviewAnswerCount = result.getReviewAnswerCount();
        this.qnaCount = result.getQnaCount();
        this.answerCount = result.getAnswerCount();

        if (list.size() > 0) {
            MatchingStatisticsResult matchingStatisticsResult = list.get(0);
            this.monthlyMatchTime = matchingStatisticsResult.getTotalTime();
            this.monthlyMatchAmount = matchingStatisticsResult.getTotalCoin();
        }
    }
}
