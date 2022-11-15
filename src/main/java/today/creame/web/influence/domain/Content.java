package today.creame.web.influence.domain;

import static lombok.AccessLevel.PROTECTED;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = PROTECTED)
@Embeddable
@Getter @ToString
public class Content {
    private String content;
    private LocalDateTime updatedDateTime;

    public Content(String content, LocalDateTime updatedDateTime) {
        this.content = content;
        this.updatedDateTime = updatedDateTime;
    }

    public Content(String content) {
        this(content, LocalDateTime.now());
    }

    public static Content secretContent(Content content, boolean secret) {
        // 내 글이면 내용이 보인다.
        if (secret) {
            return content;
        }

        return content != null
            ? new Content(null, content.updatedDateTime)
            : null;
    }
}
