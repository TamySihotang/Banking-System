package hello.service;

import hello.Domain.Account;
import hello.Domain.Deposit;
import hello.converter.DepositVOConverter;
import hello.exception.NostraException;
import hello.repository.AccountRepository;
import hello.repository.DepositRepository;
import hello.util.ErrorUtil;
import hello.vo.BaseVO;
import hello.vo.DepositVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;

    @Autowired
    DepositVOConverter depositVOConverter;

    @Autowired
    AccountRepository accountRepository;

    @Transactional

    public BaseVO deposit(DepositVO depositVO) {

        Account account = accountRepository.findBySecureId(depositVO.getAccountNumber());
        if(account == null) throw ErrorUtil.notFoundError("Account");


            Deposit depositAdd = depositVOConverter.transferVOToModel(depositVO, null);
            depositAdd.setAccountNumber(account);
            Deposit saved = depositRepository.save(depositAdd);

            Double balance = depositVO.getBalance() + account.getBalance();
            account.setBalance(balance);
            accountRepository.save(account);


        BaseVO response = new BaseVO();
        response.setId(saved.getSecureId());
        response.setVersion(saved.getVersion());
        return response;

    }

    public List<DepositVO> getAll(String accountNumber) {
        Account account = accountRepository.findBySecureId(accountNumber);
        List<Deposit> listResult = depositRepository.findByAccountNumber(account);
        List<DepositVO> listVo = new ArrayList<>();
        depositVOConverter.transferListOfModelToListOfVO(listResult, listVo);
        return listVo;
    }
}
