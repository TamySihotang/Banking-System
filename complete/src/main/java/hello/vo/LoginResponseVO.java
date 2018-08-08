package hello.vo;

import lombok.Data;

@Data
public class LoginResponseVO {
    private String firstName;
    private String lastName;
    private String accessToken;
}
