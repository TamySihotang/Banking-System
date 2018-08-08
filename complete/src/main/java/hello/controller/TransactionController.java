package hello.controller;

import hello.Domain.Account;
import hello.service.TransactionService;
import hello.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//import hello.Domain.Account;
@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/getTransaction",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> getTransaction(@RequestParam(value = "accountNumber", defaultValue = "", required = false) String accountNumber) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return transactionService.transaksi(accountNumber);
            }
        };
        return handler.getResult();
    }
}
