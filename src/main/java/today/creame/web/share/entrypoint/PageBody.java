package today.creame.web.share.entrypoint;

import lombok.Getter;

import java.util.List;

@Getter
public class PageBody<T> {
    private int code;
    private String message;
    private String traceId;
    private Long total;
    private List<T> data;

    public PageBody(int code, String message, String traceId, List<T> data, Long total) {
        this.code = code;
        this.message = message;
        this.traceId = traceId;
        this.data = data;
        this.total = total;
    }
}
