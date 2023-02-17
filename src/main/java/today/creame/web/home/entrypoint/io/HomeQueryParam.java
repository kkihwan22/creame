package today.creame.web.home.entrypoint.io;

import java.util.Optional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import today.creame.web.home.application.DisplayQueryTypes;
import today.creame.web.share.entrypoint.PaginationQueryParam;

@Getter
@Setter
@ToString
public class HomeQueryParam extends PaginationQueryParam {
    private DisplayQueryTypes type;
    private String category;
    private String keyword;

    public HomeQueryParam(Integer page, Integer size, String type, String category, String keyword) {
        super(page, size);
        this.type = DisplayQueryTypes.valueOf(Optional.ofNullable(type).orElse("ALL").toUpperCase());
        this.category = category;
        this.keyword = keyword;
    }
}
