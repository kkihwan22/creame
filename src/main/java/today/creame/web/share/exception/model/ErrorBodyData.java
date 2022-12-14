package today.creame.web.share.exception.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorBodyData<K, V> {

    private final K context;
    private final V reason;

    /**
     * @param context 는 에러가 발생한 위치에 대한 정보를 담는다.
     * @param reason 는 에러가 발생한 원인에 대한 정보를 담는다.
     */
    public ErrorBodyData(K context, V reason) {
        this.context = context;
        this.reason = reason;
    }
}