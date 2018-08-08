package hello.exception;

import hello.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * agus w
 */
@ControllerAdvice
class GlobalDefaultExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class, RuntimeException.class })
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest req, Exception e) {

        LOGGER.error(String.format("%s : Caught in Global Exception Handler: for req: %s  ",e.getLocalizedMessage(), req.getRequestURL()));
        LOGGER.error(e.getLocalizedMessage(), e);

        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        ResultVO restResponseVO = new ResultVO();
        restResponseVO.setResult(status.value());
        restResponseVO.setMessage(status.getReasonPhrase());

        return new ResponseEntity<>(restResponseVO, status);
    }
}
