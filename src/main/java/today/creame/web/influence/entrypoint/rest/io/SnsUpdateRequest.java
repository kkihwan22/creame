package today.creame.web.influence.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;
import today.creame.web.influence.domain.SnsCompany;
import today.creame.web.share.entrypoint.validator.EnumConstraint;

@Getter @ToString
public class SnsUpdateRequest {
    @EnumConstraint(enumClass = SnsCompany.class)
    private SnsCompany snsCompany;

    @NotBlank
    @URL
    private String address;
}
