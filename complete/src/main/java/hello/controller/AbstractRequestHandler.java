package hello.controller;

import hello.exception.NostraException;
import hello.util.Constants;
import hello.util.RestUtil;
import hello.util.StatusCode;
import hello.vo.ResultPageVO;
import hello.vo.ResultVO;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Map;

/**
 * agus w
 */
public abstract class AbstractRequestHandler {

    public ResponseEntity<ResultVO> getResult() {
        ResultVO result = new ResultVO();
        try {
            Object obj = processRequest();

            if (obj instanceof ResultVO){
                result = (ResultVO) obj;
                return RestUtil.getJsonResponse(result);
            }

            if (obj != null) {
                result.setMessage(StatusCode.OK.name());
                result.setResult(obj);
            }else {
                result.setMessage(StatusCode.OK.name());
                result.setResult(null);
            }
        } catch (NostraException e) {
            result.setMessage(e.getCode().name());
            result.setResult(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public abstract Object processRequest();

    public static ResponseEntity<ResultPageVO> constructListResult(Map<String, Object> pageMap) {
        ResultPageVO result = new ResultPageVO();
        try {
            Collection list = constructPageResult(pageMap, result);
            result.setResult(list);
        } catch (Exception e) {
            result.setMessage(e.getMessage());
        }
        return RestUtil.getJsonResponse(result);
    }

    public static Collection constructPageResult(Map<String, Object> map, ResultPageVO result) {
        if (map == null) {
            result.setPages("0");
            result.setElements("0");
            result.setMessage(StatusCode.DATA_NOT_FOUND.name());
            return null;
        } else {
            Collection vos = (Collection) map.get(Constants.PageParameter.LIST_DATA);
            result.setPages(String.valueOf(map.get(Constants.PageParameter.TOTAL_PAGES)));
            result.setElements(String.valueOf(map.get(Constants.PageParameter.TOTAL_ELEMENTS)));
            result.setMessage(StatusCode.OK.name());
            return vos;
        }
    }
}
