package hello.service;

import hello.Domain.Account;
import hello.Domain.User;
import hello.converter.UserVoConverter;
import hello.repository.AccountRepository;
import hello.repository.UserRepository;
import hello.util.ErrorUtil;
import hello.vo.DepositVO;
import hello.vo.TransactionVO;
import hello.vo.TransferVO;
import hello.vo.WithdrawVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DepositService depositService;

    @Autowired
    WithdrawService withdrawService;

    @Autowired
    TransferService transferService;

    @Autowired
    UserVoConverter userVoConverter;

    @Autowired
    AccountRepository accountRepository;

    public TransactionVO transaksi(String accountNumber) {

        Account account = accountRepository.findBySecureId(accountNumber);
        if(account == null) throw ErrorUtil.notFoundError("Account");

        User user = userRepository.findByAccountNumber(account);
        TransactionVO vo = userVoConverter.transferModelToVOTransaction(user,null);


        return vo;

    }
}
