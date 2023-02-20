package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class GreetingsResponse {

    private String fileUri;

    public GreetingsResponse(String fileUri) {
        this.fileUri = fileUri;
    }
}
