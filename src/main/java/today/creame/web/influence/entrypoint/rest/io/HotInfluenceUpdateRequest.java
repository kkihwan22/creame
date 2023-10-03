package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter @ToString
public class HotInfluenceUpdateRequest {

    private String bannerName;
    @NotBlank
    private String title;
    private String bannerImageUri;
    private boolean enabled;
    @NotNull
    private Integer orderNumber;

}