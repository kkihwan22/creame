package today.creame.web.member.exception;

import java.util.Collections;
import today.creame.web.share.exception.BusinessException;
import today.creame.web.share.exception.model.ErrorBodyData;

public class NotMatchedCodeException extends BusinessException {
    private final static MemberErrorCode errorCode = MemberErrorCode.NOT_MATCHED_TOKEN;

    public NotMatchedCodeException(Integer cnt) {
        super(
            errorCode.getCode(),
            errorCode.getMessage(),
            Collections.singletonList(new ErrorBodyData<>("errorCount", cnt))
        );
    }
}
