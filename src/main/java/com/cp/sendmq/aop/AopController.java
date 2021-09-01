package com.cp.sendmq.aop;

/**
 * <p>
 * 多线程
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */

import com.cp.sendmq.entity.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aop/test")
@Api(tags = "aop")
public class AopController {

    private final Logger log = LoggerFactory.getLogger(AopController.class);

    @PostMapping("/test1")
    @ApiOperation("test1测试")
    @PermissionAnnotation(title = "ygy")
    public String test1(@RequestBody Student student, @RequestParam String key) throws Exception {
        log.error("pppppppppppp");
        return "lllkkk";
    }

    @GetMapping("/test2")
    @ApiOperation("test2测试")
    public String test2(@RequestParam String key) throws Exception {
        log.error("pppppppppppp");
        return "lllkkk";
    }
}
