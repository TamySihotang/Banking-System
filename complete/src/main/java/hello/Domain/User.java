package hello.Domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER")
@DynamicUpdate
@Data
public class User extends Base {

    @Column(name = "FIRSTNAME")
    private String firstName;

    @Column(name = "LASTNAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CONTACTNUMBER")
    private String contactNumber;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "PASSWORD", length = 128, nullable = false)
    private String password;


    @Column(name = "ACTIVE")
    private Boolean active;

    @Column(name = "USERNAME", length = 100)
    private String username ;

    @Column(name = "LAST_UPDATED_PASSWORD", length = 36)
    private Date lastUpdatedPassword;

    @OneToOne
    @JoinColumn(name = "ACCOUNTNUMBER")
    private Account accountNumber;

    @Column(name = "ACCESS_TOKEN", length = 36)
    private String accessToken;

    @PrePersist
    public void prePersist() {
        super.prePersist();
        this.active = true;
        this.lastUpdatedPassword = new Date();

    }


}
