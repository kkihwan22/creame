package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import today.creame.web.influence.domain.SnsCompany;

@Getter @ToString
public class SnsUpdateRequest {
    @NotNull
    private SnsCompany snsCompany;

    @NotBlank
    @URL
    private String address;
}
