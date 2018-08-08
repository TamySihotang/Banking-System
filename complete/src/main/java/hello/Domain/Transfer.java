package hello.Domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Table(name = "TRANSFER")
@DynamicUpdate
@Data
public class Transfer extends Base{

    @ManyToOne
    @JoinColumn(name = "DESTINATIONNUMBER")
    private Account destinationNumber;

    @ManyToOne
    @JoinColumn(name = "ACCOUNTNUMBER")
    private Account accountNumber;

    @Column(name = "BALANCE")
    private Double balance;
}
