package hello.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by CiTruS on 8/21/2014.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseVO implements Serializable {

    /**
     * Secure ID / UUID
     */
    private String id;
    private Integer version;

}
