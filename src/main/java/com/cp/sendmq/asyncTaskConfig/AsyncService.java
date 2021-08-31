package com.cp.sendmq.asyncTaskConfig;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/27
 */
@Slf4j
@Service
public class AsyncService {
    //private final Logger log = LoggerFactory.getLogger(AsyncController.class);

    @Async("executor")
    public CompletableFuture<String> doSomething1(String message) throws InterruptedException {
        log.error("do something1: {}", message);
        //Thread.sleep(50000);
        return CompletableFuture.completedFuture("do something1: " + message);
    }

    @Async("executor")
    public CompletableFuture<String> doSomething2(String message) throws InterruptedException {
        log.error("do something2: {}", message);
        //Thread.sleep(5000);
        return CompletableFuture.completedFuture("; do something2: " + message);
    }

    @Async("executor")
    public CompletableFuture<String> doSomething3(String message) throws InterruptedException {
        log.error("do something3: {}", message);
        //Thread.sleep(5000);
        return CompletableFuture.completedFuture("; do something3: " + message);
    }
}
