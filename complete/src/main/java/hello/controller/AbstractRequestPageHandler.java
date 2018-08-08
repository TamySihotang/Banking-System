package hello.controller;


import hello.exception.NostraException;
import hello.util.RestUtil;
import hello.vo.ResultPageVO;
import org.springframework.http.ResponseEntity;

/**
 * agus w
 */
public abstract class AbstractRequestPageHandler {

    public ResponseEntity<ResultPageVO> getResult() {
        ResultPageVO result = new ResultPageVO();
        try {
            return processRequest();
        } catch (NostraException e) {
            result.setMessage(e.getCode().name());
            result.setResult(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public abstract ResponseEntity<ResultPageVO> processRequest();
}
