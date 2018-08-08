package hello.repository;

import hello.Domain.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by fani on 5/2/15.
 */
@NoRepositoryBean
public interface BaseRepository<T extends Base> extends JpaRepository<T, Integer>, JpaSpecificationExecutor<T> {
    public T findBySecureId(String secureId);
    public T findById(Integer id);
//    public void deleteRow(T deleteObject);
//    public void deleteRow(Iterable<? extends T> entities);

    boolean exists(Integer integer);
}
