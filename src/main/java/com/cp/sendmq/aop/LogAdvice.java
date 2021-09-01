package com.cp.sendmq.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/31
 */
@Aspect
@Component
public class LogAdvice {
    //@Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    //private void logAdvicePointcut() {}
    //@Before("logAdvicePointcut()")
    //public void logAdvice(){
    //    // 这里只是一个示例，你可以写任何处理逻辑
    //    System.out.println("get请求的advice触发了");
    //}
}
