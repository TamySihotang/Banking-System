package hello.controller;

import hello.service.WithdrawService;
import hello.vo.ResultVO;
import hello.vo.WithdrawVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/withdraw")
public class WithdrawController {
    @Autowired
    WithdrawService withdrawService;

    @RequestMapping(value = "/withdraw",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> withdraw(@RequestBody WithdrawVO withdrawVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return withdrawService.withdraw(withdrawVO);
            }
        };
        return handler.getResult();
    }
}
