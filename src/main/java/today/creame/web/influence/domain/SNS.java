package today.creame.web.influence.domain;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import today.creame.web.influence.domain.converter.SnsCompanyToStringConverter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
@Getter @ToString
public class SNS {

    @Convert(converter = SnsCompanyToStringConverter.class)
    private SnsCompany company;
    private String address;

    public SNS(SnsCompany company, String address) {
        this.company = company;
        this.address = address;
    }
}
