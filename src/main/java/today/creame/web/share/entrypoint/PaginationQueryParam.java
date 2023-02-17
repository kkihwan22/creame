package today.creame.web.share.entrypoint;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.PageRequest;

@Getter
@ToString
public abstract class PaginationQueryParam {

    protected PageRequest pageRequest;

    public PaginationQueryParam(Integer page, Integer size) {
        if (page == null) page = 0;
        if (size == null) size = 20;
        this.pageRequest = PageRequest.of(page, size);
    }
}
