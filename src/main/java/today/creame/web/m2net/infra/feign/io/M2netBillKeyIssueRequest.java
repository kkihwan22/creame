package today.creame.web.m2net.infra.feign.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class M2netBillKeyIssueRequest {
    private String oid;
    private String cardno;
    private String expMonth;
    private String expYear;
    private String socno;
    private String pass;
    private String usernm;
    private String item;
    @Default
    private int amount = 10000;
    @Default
    private int coinamt = 10000;
    private String membid;
    private String telno;
    private String pushurl;
}