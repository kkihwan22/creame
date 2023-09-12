package today.creame.web.matching.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MatchingSearchRequest {
    private Long id;
    private String nickname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDt;

}
