package hello.vo;

import lombok.Data;

/**
 * Created by fani on 4/23/15.
 */
@Data
public class ResultVO {

    private String message;
    private Object result;

    public ResultVO() { }

    public ResultVO(String message, Object result) {
        this.message = message;
        this.result = result;
    }

}
