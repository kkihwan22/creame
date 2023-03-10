package today.creame.web.payments.entrypoint.rest.io;

import java.util.Arrays;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import today.creame.web.payments.domain.CreditCard;
import today.creame.web.share.entrypoint.exception.BadRequestParameterException;
import today.creame.web.share.exception.model.ErrorBodyData;

@Getter
@ToString
public class BillKeyIssueRequest {

    @NotNull
    private Boolean personal;

    @NotBlank
    private String serial;

    @NotBlank
    @Length(min = 16, max = 16)
    private String cardno;

    @NotBlank
    @Length(min = 2, max = 2)
    private String month;

    @NotBlank
    @Length(min = 2, max = 2)
    private String year;

    @NotBlank
    @Length(min = 2, max = 2)
    private String cardPassword;

    @NotBlank
    @Length(min = 6, max = 6)
    private String paymentPassword;

    public BillKeyIssueRequest(Boolean personal, String serial, String cardno, String month, String year, String cardPassword, String paymentPassword) {
        this.personal = personal;
        this.serial = serial;
        this.cardno = cardno;
        this.month = month;
        this.year = year;
        this.cardPassword = cardPassword;
        this.paymentPassword = paymentPassword;
    }

    public void validSerial() {
        boolean condition = personal
            ? serial.length() != 6
            : serial.length() != 12;

        if (condition) {
            throw new BadRequestParameterException(Arrays.asList(new ErrorBodyData("card.serial", "카드번호의 길이가 유효하지 않습니다.")));
        }
    }

    public CreditCard of() {
        return new CreditCard(cardno, year, month, serial, cardPassword, paymentPassword);
    }
}