package hello.repository;

import hello.Domain.Account;
import hello.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
    User findByAccountNumber (Account accountNumber);
    @Query("SELECT u FROM User u WHERE u.accessToken =:accessToken AND u.deleted = false")
    public User findByAccessToken(@Param("accessToken") String accessToken);

    @Query("SELECT u.accessToken FROM User u WHERE u.email =:email AND u.deleted = false")
    public String findAccessTokenByEmail(@Param("email") String email);
}
