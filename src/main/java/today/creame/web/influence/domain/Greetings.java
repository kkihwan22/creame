package today.creame.web.influence.domain;

import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = PROTECTED)
@Embeddable
@Getter @ToString
public class Greetings {
    private Long fileResourceId;
    private String fileResourceUri;

    public Greetings(Long id, String fileResourceUri) {
        this.fileResourceId = id;
        this.fileResourceUri = fileResourceUri;
    }
}
