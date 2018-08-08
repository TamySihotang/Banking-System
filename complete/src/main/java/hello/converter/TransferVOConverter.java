package hello.converter;

import hello.Domain.Account;
import hello.Domain.Transfer;
import hello.repository.AccountRepository;
import hello.util.ExtendedSpringBeanUtil;
import hello.vo.TransferVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransferVOConverter extends BaseVOConverter<TransferVO, TransferVO, Transfer> implements IBaseVOConverter<TransferVO, TransferVO, Transfer>{
    @Autowired
    AccountRepository accountRepository;

    @Override
    public TransferVO transferModelToVO(Transfer model, TransferVO vo) {
        if (null == vo) vo = new TransferVO();

        super.transferModelToVO(model, vo);
        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"destinationNumber","accountNumber","balance"}
        );

        Account account = accountRepository.findBySecureId(model.getAccountNumber().getSecureId());
        Account accountDestination = accountRepository.findBySecureId(model.getDestinationNumber().getSecureId());
        vo.setAccountNumber(account.getSecureId());
        vo.setDestinationNumber(accountDestination.getSecureId());
        return vo;
    }

    @Override
    public Transfer transferVOToModel(TransferVO vo, Transfer model) {
        if (null == model) model = new Transfer();

        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"destinationNumber","accountNumber","balance"});

        return model;
    }
}
