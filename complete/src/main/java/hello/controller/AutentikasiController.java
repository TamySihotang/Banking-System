package hello.controller;

import hello.service.AutentikasiService;
import hello.vo.LoginVO;
import hello.vo.ResultVO;
import hello.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class AutentikasiController {
    @Autowired
    AutentikasiService autentikasiService;

    @RequestMapping(value = "/registrasi",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ResultVO> registrasi(@RequestBody UserVO userVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return autentikasiService.registratrion(userVO);
            }
        };
        return handler.getResult();
    }

    @RequestMapping(value = "/login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultVO> login(@RequestBody LoginVO loginVO) {
        AbstractRequestHandler handler = new AbstractRequestHandler() {
            @Override
            public Object processRequest() {
                return autentikasiService.loginUser(loginVO);
            }
        };
        return handler.getResult();
    }
}
