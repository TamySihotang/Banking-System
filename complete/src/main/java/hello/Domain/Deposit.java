package hello.Domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "DEPOSIT")
@DynamicUpdate
@Data
public class Deposit extends Base {

    @ManyToOne
    @JoinColumn(name = "ACCOUNTNUMBER")
    private Account accountNumber;

    @Column(name = "BALANCE")
    private Double balance;
}
