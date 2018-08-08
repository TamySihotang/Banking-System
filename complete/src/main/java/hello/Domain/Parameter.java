package hello.Domain;

import lombok.Data;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Created by antin on 12/22/15.
 */
@Entity
@Table(name = "PARAMETER",
        indexes = {
                @Index(columnList = "CODE", name = "UK_CODE", unique = true)
        })
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "project")
@DynamicUpdate
@Data
public class Parameter extends Base {
    @Column(name = "CODE", length = 150, nullable = false)
    private String code;

    @Column(name = "VALUE", length = 255, nullable = false)
    private String value;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

}
