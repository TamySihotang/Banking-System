package hello.repository;

import hello.Domain.Account;
import hello.Domain.Transfer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends BaseRepository<Transfer> {
    List<Transfer> findByAccountNumber (Account accountNumber);
}
