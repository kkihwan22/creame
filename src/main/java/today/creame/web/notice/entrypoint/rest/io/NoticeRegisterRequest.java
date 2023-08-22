package today.creame.web.notice.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class NoticeRegisterRequest {
    @NotBlank(message = "제목은 필수 입력 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 입니다.")
    private String content;
}
