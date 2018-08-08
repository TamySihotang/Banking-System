package hello.converter;

import hello.Domain.Account;
import hello.Domain.Withdraw;
import hello.repository.AccountRepository;
import hello.util.ExtendedSpringBeanUtil;
import hello.vo.WithdrawVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawVOConverter extends BaseVOConverter<WithdrawVO, WithdrawVO, Withdraw> implements IBaseVOConverter<WithdrawVO, WithdrawVO, Withdraw> {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public WithdrawVO transferModelToVO(Withdraw model, WithdrawVO vo) {
        if (null == vo) vo = new WithdrawVO();

        super.transferModelToVO(model, vo);
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"accountNumber","balance"}
        );

        Account account = accountRepository.findBySecureId(model.getAccountNumber().getSecureId());
        vo.setAccountNumber(account.getSecureId());

        return vo;
    }

    @Override
    public Withdraw transferVOToModel(WithdrawVO vo, Withdraw model) {
        if (null == model) model = new Withdraw();

        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"accountNumber","balance"});

        return model;
    }

}
