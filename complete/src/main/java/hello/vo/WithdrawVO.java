package hello.vo;

import lombok.Data;

@Data
public class WithdrawVO {
    private String accountNumber;
    private Double balance;
}
