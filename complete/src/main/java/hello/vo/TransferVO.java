package hello.vo;

import lombok.Data;

@Data
public class TransferVO {
    private String accountNumber;
    private String destinationNumber;
    private Double balance;
}
