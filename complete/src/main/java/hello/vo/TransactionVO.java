package hello.vo;

import lombok.Data;

import java.util.Collection;

@Data
public class TransactionVO {
    private String firstName;
    private String lastName;
    private String address;
    private String contactNumber;
    private String email;
    private String username;
    private Double balance;
    private Collection<DepositVO> depositVOS;
    private Collection<WithdrawVO> withdrawVOS;
    private Collection<TransferVO>transferVOS;
}
