package hello.converter;

import hello.Domain.Account;
import hello.Domain.Deposit;
import hello.repository.AccountRepository;
import hello.util.ExtendedSpringBeanUtil;
import hello.vo.DepositVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositVOConverter extends BaseVOConverter<DepositVO, DepositVO, Deposit> implements IBaseVOConverter<DepositVO, DepositVO, Deposit> {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public DepositVO transferModelToVO(Deposit model, DepositVO vo) {
        if (null == vo) vo = new DepositVO();

        super.transferModelToVO(model, vo);
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"accountNumber","balance"}
        );

        Account account = accountRepository.findBySecureId(model.getAccountNumber().getSecureId());
        vo.setAccountNumber(account.getSecureId());
        return vo;
    }

    @Override
    public Deposit transferVOToModel(DepositVO vo, Deposit model) {
        if (null == model) model = new Deposit();

        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"accountNumber","balance"});

        return model;
    }

}
