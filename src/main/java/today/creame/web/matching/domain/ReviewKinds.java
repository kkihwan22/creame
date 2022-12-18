package today.creame.web.matching.domain;

import lombok.Getter;

public enum ReviewKinds {

    KINDNESS("친절해요"),
    SYMPATHY("공감해요"),
    DEEPLY("깊이가 있어요"),
    COMFORTABLE("편안해요"),
    REALITY("현실적이예요"),
    VOICE("목소리가 좋아요"),
    HONEST("정직해요"),
    LISTEN("잘 들어줘요")

    ;

    @Getter
    private String label;

    ReviewKinds(String label) {
        this.label = label;
    }
}
