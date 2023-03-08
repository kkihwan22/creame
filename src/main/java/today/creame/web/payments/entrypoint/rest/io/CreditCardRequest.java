package today.creame.web.payments.entrypoint.rest.io;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Getter
@ToString
public class CreditCardRequest {

    @NotNull
    private CreditCardType type;

    @NotBlank
    private String socialId;

    @NotBlank
    @Length(min = 12, max = 12)
    private String serial;

    @NotBlank
    @Length(min = 2, max = 2)
    private String month;

    @NotBlank
    @Length(min = 2, max = 2)
    private String year;

    @NotBlank
    @Length(min = 2, max = 2)
    private String password;

    @NotBlank
    private String name;

    public CreditCardRequest(CreditCardType type, String serial, String month, String year, String password, String name) {
        this.type = type;
        this.serial = serial;
        this.month = month;
        this.year = year;
        this.password = password;
        this.name = name;
    }

    public boolean isNotValidSerial() {
        return CreditCardType.PERSON == type
            ? socialId.length() != 6
            : socialId.length() != 12
            ;
    }
}
