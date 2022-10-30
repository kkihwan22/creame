package today.creame.web.influence.entrypoint.rest.io;

import lombok.Getter;
import lombok.ToString;
import today.creame.web.influence.domain.SNS;

@Getter @ToString
public class SnsGetResponse {

    private String snsCompany;
    private String address;

    public SnsGetResponse(SNS sns) {
        this.snsCompany = sns.getCompany().name();
        this.address = sns.getAddress();
    }
}
