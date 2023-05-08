package today.creame.web.share.exception.model;

import lombok.Getter;

@Getter
public class MetaBodyData<T> {
    private T body;

    public MetaBodyData(T body) {
        this.body = body;
    }
}
