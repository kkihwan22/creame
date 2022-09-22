package today.creame.web.share.entrypoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import today.creame.web.share.exception.BusinessException;
import today.creame.web.share.exception.UnknownException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * TODO
 * WebRequest or HttpServletRequest 에 대해서 정확히 파악
 * TODO
 * private 메소드로 URI 정보 / 헤더 / 전달 데이터를 로그에 남기는 코드 작성
 * TODO
 * Cause 에 정보가 잘 담기는지 확인 및 테스트 ( trace 가 되는지가 중요하다.)
 */
@RestControllerAdvice
public class AppRestControllerAdvisor {
    private final Logger LOG = LoggerFactory.getLogger(AppRestControllerAdvisor.class);

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBody<List<Error>>> handleBusinessException(RuntimeException e) {
        BusinessException exception = (BusinessException) e;
        LOG.error("[ error ] point : {}", e.getClass().getSimpleName());
        LOG.error("[ error ] message : {}", e.getMessage());

        List<Error> errors = Optional.ofNullable(exception.getErrors()).orElse(Collections.emptyList());
        return ResponseEntity.badRequest()
                .body(ResponseBodyFactory.failure(
                        exception.getCode(), exception.getMessage(), "", errors));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ResponseBody<SimpleResponseData<String>>> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        LOG.error("[ error ] point : {}", e.getClass().getSimpleName());
        LOG.error("[ error ] message : {}", e.getMessage());
        // TODO : 구성하기
        return ResponseEntity.internalServerError().body(ResponseBodyFactory.failure(-1, "관리자에게 문의하세요", "", new SimpleResponseData<>("failure")));
    }

    @ExceptionHandler(UnknownException.class)
    public ResponseEntity<ResponseBody<Void>> handleUnknownException(RuntimeException e) {
        UnknownException exception = (UnknownException) e;
        LOG.error("[ error ] point : {}", e.getClass().getSimpleName());
        LOG.error("[ error ] message : {}", e.getMessage());

        return ResponseEntity.badRequest()
                .body(ResponseBodyFactory.failure(
                        exception.getCode(), exception.getMessage(), "", null));
    }
}