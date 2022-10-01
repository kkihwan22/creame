package today.creame.web.share.entrypoint;

import lombok.Getter;
import lombok.Setter;

public class SimpleBodyData<T> {

    @Getter @Setter
    private T result;

    public SimpleBodyData(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SimpleResponseData{" +
                "result=" + result +
                '}';
    }
}
