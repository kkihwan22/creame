package today.creame.web.faq.entrypoint.rest.io;

import lombok.AllArgsConstructor;
import lombok.Getter;
import today.creame.web.faq.domain.FaqCategory;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class FaqRegisterRequest {
    @NotBlank(message = "제목은 필수 입력 입니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력 입니다.")
    private String content;

    @NotNull(message = "카테고리는 필수 입력 입니다.")
    private FaqCategory category;

    @NotNull(message = "노출 순서는 필수 입력 입니다.")
    private int orderNumber;

}
