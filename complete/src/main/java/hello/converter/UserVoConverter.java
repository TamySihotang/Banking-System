package hello.converter;

import hello.Domain.Account;
import hello.Domain.User;
import hello.repository.AccountRepository;
import hello.service.DepositService;
import hello.service.TransferService;
import hello.service.WithdrawService;
import hello.util.ExtendedSpringBeanUtil;
import hello.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserVoConverter extends BaseVOConverter<UserVO, UserVO, User> implements IBaseVOConverter<UserVO, UserVO, User> {

    @Autowired
    DepositService depositService;

    @Autowired
    WithdrawService withdrawService;

    @Autowired
    TransferService transferService;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public User transferVOToModel(UserVO vo, User model) {
        if (null == model) model = new User();
        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"firstName","lastName","address","contactNumber","email","password","active","username","lastUpdatedPassword","accountNumber"});
        model.setPassword(new BCryptPasswordEncoder().encode(vo.getPassword()));
        return model;
    }

    @Override
    public UserVO transferModelToVO(User model, UserVO vo) {

        if (null == vo) vo = new UserVO();
        super.transferModelToVO(model, vo);
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"firstName","lastName","address","contactNumber","email","password","active","username","lastUpdatedPassword","accountNumber"});
        return vo;
    }

    public TransactionVO transferModelToVOTransaction(User model, TransactionVO vo) {

        if (null == vo) vo = new TransactionVO();
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"firstName","lastName","address","contactNumber","email","password","active","username","lastUpdatedPassword","accountNumber"});

        Account account = accountRepository.findBySecureId(model.getAccountNumber().getSecureId());
        vo.setBalance(account.getBalance());
        List<DepositVO> depositVOS = depositService.getAll(model.getAccountNumber().getSecureId());
        vo.setDepositVOS(depositVOS);

        List<WithdrawVO> withdrawVOS = withdrawService.getAll(model.getAccountNumber().getSecureId());
        vo.setWithdrawVOS(withdrawVOS);

        List<TransferVO> transferVOS = transferService.getAll(model.getAccountNumber().getSecureId());
        vo.setTransferVOS(transferVOS);
        return vo;
    }




}
