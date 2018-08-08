package hello.service;

import hello.Domain.Account;
import hello.Domain.Deposit;
import hello.Domain.User;
import hello.converter.AccountVOConverter;
import hello.converter.DepositVOConverter;
import hello.converter.UserVoConverter;
import hello.exception.NostraException;
import hello.repository.AccountRepository;
import hello.repository.DepositRepository;
import hello.repository.UserRepository;
import hello.util.ErrorUtil;
import hello.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AutentikasiService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserVoConverter userVoConverter;

    @Autowired
    AccountVOConverter accountVOConverter;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    DepositVOConverter depositVOConverter;

    @Autowired
    DepositRepository depositRepository;


    @Transactional
    public AuthentikasiVO registratrion(UserVO userVO){

        User user = userRepository.findByUsername(userVO.getUsername());
        if(user != null) {
            if(user.getUsername().equalsIgnoreCase(userVO.getUsername()))
                throw new NostraException("Username already exist");
        }

        final Account[] saveAccount = {new Account()};
        User userAdd = userVoConverter.transferVOToModel(userVO,null);
        userVO.getAccountNumber().forEach(accountVO ->{
            Account accountAdd = accountVOConverter.transferVOToModel(accountVO,null);
             saveAccount[0] = accountRepository.save(accountAdd);
           Deposit deposit = new Deposit();
           deposit.setBalance(accountVO.getBalance());
           deposit.setAccountNumber(saveAccount[0]);
           deposit = depositRepository.save(deposit);
        });
        userAdd.setAccountNumber(saveAccount[0]);
        User saved = userRepository.save(userAdd);

        AuthentikasiVO response = new AuthentikasiVO();
        response.setAccountId(saved.getAccountNumber().getSecureId());
        return response;

    }

    public LoginResponseVO loginUser(LoginVO loginVO){

        User user = userRepository.findByUsername(loginVO.getUsername());

        if(user == null) {
            throw new NostraException("Username/password not valid");
        }

        return doLoginUser(loginVO.getPassword(), user);
    }

    private LoginResponseVO doLoginUser(String pass, User user){

        if (new BCryptPasswordEncoder().matches(pass, user.getPassword())) {
            LoginResponseVO loginResponseVO = new LoginResponseVO();
            loginResponseVO.setFirstName(user.getFirstName());
            loginResponseVO.setLastName(user.getLastName());

            return loginResponseVO;
        }
        else{
            throw new NostraException("Username or password not valid");
        }
    }

}
