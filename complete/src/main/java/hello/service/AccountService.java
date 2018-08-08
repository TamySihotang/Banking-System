package hello.service;

import hello.Domain.Account;
import hello.converter.AccountVOConverter;
import hello.repository.AccountRepository;
import hello.vo.AccountVO;
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
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountVOConverter accountVOConverter;

    public Map<String, Object> search(String id, Integer page, Integer limit, String sortBy, String direction) {

        Page<Account> resultPage = null;
        List<Account> models;
        Collection<AccountVO> vos;

        sortBy = StringUtils.isEmpty(sortBy) ? "id" : sortBy;
        direction = StringUtils.isEmpty(direction) ? "desc" : direction;
        Pageable pageable = new PageRequest(page, limit, AbstractBaseService.getSortBy(sortBy, direction));
//        Pageable pageable = new PageRequest(page, limit, null);

        if(StringUtils.isEmpty(id)){
            resultPage = accountRepository.findAll(pageable);

        }else if(!StringUtils.isEmpty(id)){
            resultPage = accountRepository.findBySecureId(id,pageable);

        }

        models = resultPage.getContent();
        vos = accountVOConverter.transferListOfModelToListOfVO(models, new ArrayList<>());
        return AbstractBaseService.constructMapReturn(vos, resultPage.getTotalElements(), resultPage.getTotalPages());

    }

}
