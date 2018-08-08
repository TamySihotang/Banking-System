package hello.util;

import com.google.gson.Gson;
import hello.vo.ParamVO;
import hello.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by derryaditiya on 2/9/17.
 */
@Component
public class WebServiceUtil {

    public static final Logger logger = LoggerFactory.getLogger(WebServiceUtil.class);

    public ResultVO requestPost (String url, Object payload) {
        String jsonRequest = new Gson().toJson(payload);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> payloadHttpEntity = new HttpEntity<String>(jsonRequest, headers);

        ResultVO resultVO = new ResultVO();
        try {
            RestTemplate restTemplate = new RestTemplate();
            resultVO = restTemplate.postForObject(url, payloadHttpEntity, ResultVO.class);
        }catch (Exception e){
            logger.error(StatusCode.ERROR.name(), e);
            resultVO.setMessage(StatusCode.ERROR.name());
            resultVO.setResult(e);
            return resultVO;
        }

        return resultVO;
    }

    public ResultVO requestGet (String url, List<ParamVO> paramVOList) {
        String urlParam = "";
        String urlComplete = "";
        if(paramVOList != null) {
            for (ParamVO param : paramVOList) {
                urlParam = urlParam + param.getKey() + "=" + param.getValue() + "&";
            }

            urlComplete = url + "?" + urlParam;
        } else {
            urlComplete = url;
        }
        ResultVO resultVO = new ResultVO();

        try {
            RestTemplate restTemplate = new RestTemplate();
            resultVO = restTemplate.getForObject(urlComplete, ResultVO.class);
        }catch (Exception e){
            logger.error(StatusCode.ERROR.name(), e);
            resultVO.setMessage(StatusCode.ERROR.name());
            resultVO.setResult(e);
            return resultVO;
        }

        return resultVO;
    }

}
