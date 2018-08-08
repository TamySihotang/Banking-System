package hello.service;

import hello.Domain.Account;
import hello.Domain.Withdraw;
import hello.converter.WithdrawVOConverter;
import hello.repository.AccountRepository;
import hello.repository.WithdrawRepository;
import hello.util.ErrorUtil;
import hello.vo.BaseVO;
import hello.vo.WithdrawVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WithdrawService {
    @Autowired
    WithdrawRepository withdrawRepository;

    @Autowired
    WithdrawVOConverter withdrawVOConverter;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public BaseVO withdraw(WithdrawVO withdrawVO) {

        Account account = accountRepository.findBySecureId(withdrawVO.getAccountNumber());
        if(account == null) throw ErrorUtil.notFoundError("Account");


        Withdraw withdrawAdd = withdrawVOConverter.transferVOToModel(withdrawVO, null);
        withdrawAdd.setAccountNumber(account);
        Withdraw saved = withdrawRepository.save(withdrawAdd);

        account.setBalance(account.getBalance() - withdrawVO.getBalance());
        accountRepository.save(account);


        BaseVO response = new BaseVO();
        response.setId(saved.getSecureId());
        response.setVersion(saved.getVersion());
        return response;

    }

    public List<WithdrawVO> getAll(String accountNumber) {
        Account account = accountRepository.findBySecureId(accountNumber);
        List<Withdraw> listResult = withdrawRepository.findByAccountNumber(account);
        List<WithdrawVO> listVo = new ArrayList<>();
        withdrawVOConverter.transferListOfModelToListOfVO(listResult, listVo);
        return listVo;
    }
}
