package today.creame.web.m2net.infra.feign.io;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class M2netBillKeyIssueResponse {
    private String reqResult;
    private String resultmessage;

    @JsonProperty(value = "BillKey")
    private String BillKey;

    public M2netBillKeyIssueResponse(String reqResult, String resultmessage, String billKey) {
        this.reqResult = reqResult;
        this.resultmessage = resultmessage;
        this.BillKey = billKey;
    }
}