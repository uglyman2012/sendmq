package com.cp.sendmq.asyncTaskConfig;

/**
 * <p>
 * 多线程
 * </p>
 *
 * @author yang
 * @since 2021/08/10
 */

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("Async/test")
@Api(tags = "Async")
public class AsyncController {

    private final Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/test1")
    @ApiOperation("test1测试")
    //@Transactional(rollbackFor = Exception.class)
    public String test1() throws Exception {
        log.error("pppppppppppppp");
        CompletableFuture<String> createOrder = asyncService.doSomething1("create order");
        CompletableFuture<String> reduceAccount = asyncService.doSomething2("reduce account");
        CompletableFuture<String> saveLog = asyncService.doSomething3("save log");

        // 等待所有任务都执行完
        CompletableFuture.allOf(createOrder, reduceAccount, saveLog).join();
        // 获取每个任务的返回结果
        String result = createOrder.get() + reduceAccount.get() + saveLog.get();
        return result;
    }

}
