package today.creame.web.share.entrypoint;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

public class SimpleResponseData <T> {

    @Getter @Setter
    private T result;

    public SimpleResponseData(T result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SimpleResponseData{" +
                "result=" + result +
                '}';
    }
}
