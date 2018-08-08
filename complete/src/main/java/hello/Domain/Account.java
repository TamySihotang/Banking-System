package hello.Domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@DynamicUpdate
@Data
public class Account extends Base{

    @Column(name = "BALANCE")
    private Double balance;

}
