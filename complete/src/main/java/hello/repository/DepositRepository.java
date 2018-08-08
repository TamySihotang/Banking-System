package hello.repository;

import hello.Domain.Account;
import hello.Domain.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends CrudRepository<Deposit, Long> {
    List<Deposit> findByAccountNumber (Account accountNumber);

    Deposit findByAccountNumberContaining (Account accountNumber);
}
