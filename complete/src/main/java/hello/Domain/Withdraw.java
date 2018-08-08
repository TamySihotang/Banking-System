package hello.Domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "WITHDRAW")
@DynamicUpdate
@Data
public class Withdraw extends Base{

    @ManyToOne
    @JoinColumn(name = "ACCOUNTNUMBER")
    private Account accountNumber;

    @Column(name = "BALANCE")
    private Double balance;
}
