package hello.vo;

import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
public class UserVO extends BaseVO {

    private String firstName;

    private String lastName;

    private String address;

    private String contactNumber;

    private String email;

    private String password;

    private Boolean active;

    private String username ;

    private Boolean immediateChangePassword;

    private Date lastUpdatedPassword;

    private Collection<AccountVO> accountNumber;

}
