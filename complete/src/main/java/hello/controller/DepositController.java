package hello.controller;

import hello.service.DepositService;
import hello.vo.DepositVO;
import hello.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/deposit")
public class DepositController {
    @Autowired
    DepositService depositService;

    @RequestMapping(value = "/deposit",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> deposit(@RequestBody DepositVO depositVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return depositService.deposit(depositVO);
            }
        };
        return handler.getResult();
    }
}
