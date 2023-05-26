package today.creame.web.member.social.exception;

import today.creame.web.share.exception.BusinessException;

public class ApplePrivateKeyParserFailException extends BusinessException {

    private final static SocialErrorCode errorCode = SocialErrorCode.APPLE_PRIVATE_KEY_PARSER_FAIL;

    public ApplePrivateKeyParserFailException() {
        super(errorCode.getCode(), errorCode.getMessage());
    }
}
