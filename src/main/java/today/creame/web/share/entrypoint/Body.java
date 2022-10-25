package today.creame.web.share.entrypoint;

import java.util.Objects;
import lombok.Getter;
import lombok.ToString;

@Getter @ToString
public class Body<T> {
    private int code;
    private String message;
    private String traceId;
    private T data;

    public Body(int code, String message, String traceId, T data) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Body<?> that = (Body<?>) o;
        return code == that.code && Objects.equals(message, that.message) && Objects.equals(traceId, that.traceId) && Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message, traceId, data);
    }
}
