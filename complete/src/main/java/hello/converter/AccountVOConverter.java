package hello.converter;

import hello.Domain.Account;
import hello.util.ExtendedSpringBeanUtil;
import hello.vo.AccountVO;
import org.springframework.stereotype.Component;

@Component
public class AccountVOConverter extends BaseVOConverter<AccountVO, AccountVO, Account> implements IBaseVOConverter<AccountVO, AccountVO, Account>{

    @Override
    public AccountVO transferModelToVO(Account model, AccountVO vo) {
        if (null == vo) vo = new AccountVO();

        super.transferModelToVO(model, vo);
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"balance"}
        );

        return vo;
    }

    @Override
    public Account transferVOToModel(AccountVO vo, Account model) {
        if (null == model) model = new Account();

        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"balance"});

        return model;
    }
}
