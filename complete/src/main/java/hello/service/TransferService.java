package hello.service;

import hello.Domain.Account;
import hello.Domain.Transfer;
import hello.converter.TransferVOConverter;
import hello.repository.AccountRepository;
import hello.repository.TransferRepository;
import hello.util.ErrorUtil;
import hello.vo.BaseVO;
import hello.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransferService {
    @Autowired
    TransferRepository transferRepository;

    @Autowired
    TransferVOConverter transferVOConverter;

    @Autowired
    AccountRepository accountRepository;

    @Transactional

    public BaseVO transfer(TransferVO transferVO) {

        Account account = accountRepository.findBySecureId(transferVO.getAccountNumber());
        if(account == null) throw ErrorUtil.notFoundError("Account");

        Account accountDestination = accountRepository.findBySecureId(transferVO.getDestinationNumber());
        if(accountDestination == null) throw ErrorUtil.notFoundError("Destination Account");

        Transfer transferAdd = transferVOConverter.transferVOToModel(transferVO, null);
        transferAdd.setAccountNumber(account);
        transferAdd.setDestinationNumber(accountDestination);
        Transfer saved = transferRepository.save(transferAdd);

        account.setBalance(account.getBalance() - transferVO.getBalance());
        accountRepository.save(account);

        accountDestination.setBalance(accountDestination.getBalance() + transferVO.getBalance());
        accountRepository.save(accountDestination);

        BaseVO response = new BaseVO();
        response.setId(saved.getSecureId());
        response.setVersion(saved.getVersion());
        return response;

    }

    public List<TransferVO> getAll(String accountNumber) {
        Account account = accountRepository.findBySecureId(accountNumber);
        List<Transfer> listResult = transferRepository.findByAccountNumber(account);
        List<TransferVO> listVo = new ArrayList<>();
        transferVOConverter.transferListOfModelToListOfVO(listResult, listVo);
        return listVo;
    }
}
