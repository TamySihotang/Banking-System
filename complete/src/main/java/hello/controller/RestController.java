package hello.controller;

import hello.vo.ResultVO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author aguswinarno, andri.khrisharyadi@gmail.com
 */
public interface RestController<Z, R extends ResultVO> extends RestPageController {

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public abstract ResponseEntity<R> add(@RequestBody Z vo);

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public abstract ResponseEntity<R> edit(@PathVariable("id") String secureId, @RequestBody Z vo);

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public abstract ResponseEntity<R> delete(@PathVariable("id") String secureId);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public abstract ResponseEntity<R> findById(@PathVariable("id") String secureId);

}
