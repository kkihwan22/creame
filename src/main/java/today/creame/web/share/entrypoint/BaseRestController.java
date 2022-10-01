package today.creame.web.share.entrypoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import today.creame.web.share.entrypoint.exception.BadRequestParameterException;
import today.creame.web.share.exception.model.ErrorBodyData;

import java.util.*;
import java.util.stream.*;

public interface BaseRestController {
    Logger log = LoggerFactory.getLogger(BaseRestController.class);
    default void hasError(BindingResult bindingResult) {

        if (!bindingResult.hasErrors()) {
            return;
        }

        List<ErrorBodyData> errors = bindingResult.getFieldErrors()
                .stream()
                .map(err -> new ErrorBodyData(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());
        log.debug("errors: {}", errors);
        throw new BadRequestParameterException(errors);
    }
}
