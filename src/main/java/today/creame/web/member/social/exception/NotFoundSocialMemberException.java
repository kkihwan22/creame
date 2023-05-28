package today.creame.web.member.social.exception;

import today.creame.web.member.social.provider.io.ProviderProfileResult;
import today.creame.web.share.exception.BusinessException;
import today.creame.web.share.exception.model.MetaBodyData;

public class NotFoundSocialMemberException extends BusinessException {

    private final static SocialErrorCode errorCode = SocialErrorCode.NOT_FOUND_SOCIAL_MEMBER;

    public NotFoundSocialMemberException(ProviderProfileResult result) {
        super(errorCode.getCode(), errorCode.getMessage(), new MetaBodyData<>(result));
    }
}
