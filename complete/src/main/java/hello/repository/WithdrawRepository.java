package hello.repository;

import hello.Domain.Account;
import hello.Domain.Withdraw;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WithdrawRepository extends BaseRepository<Withdraw> {
    List<Withdraw> findByAccountNumber (Account accountNumber);
}
