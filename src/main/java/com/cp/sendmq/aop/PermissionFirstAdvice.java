package com.cp.sendmq.aop;

import com.alibaba.fastjson.JSON;
import com.cp.sendmq.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *
 * </p>
 *
 * @author yang
 * @since 2021/08/31
 */
@Slf4j
@Aspect
@Component
@Order(1)
public class PermissionFirstAdvice {
    @Pointcut("@annotation(com.cp.sendmq.aop.PermissionAnnotation)")
    private void permissionCheck() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void logAdvicePointcut() {
    }

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {

        //获得入参
        Object[] args = joinPoint.getArgs();
        String id = ((Student) args[0]).getId();
        String name = ((Student) args[0]).getName();
        String s = args[1].toString();


        if (id != null) {
            Student student = new Student();
            student.setId("989");
            args[0] = student;
            //目标方法
            Object proceed = joinPoint.proceed(args);
            return JSON.toJSONString(student);
        }
        //目标方法
        //return joinPoint.proceed();
        return "ppi";
    }

    /**
     * 注意:返回后通知（@AfterReturning）
     * 和抛出异常后通知（@AfterThrowing）
     * 的方法中不能使用ProceedingJoinPoint，
     * 使用JoinPoint即可解决
     *
     * @param joinPoint
     * @throws Throwable
     */
    @Before("logAdvicePointcut()")
    public void permissionCheckOne(JoinPoint joinPoint) {

        log.info("====doBefore方法进入了====");
        //# 可以发挥反射的功能获取关于类的任何信息，例如获取类名如下
        String className = joinPoint.getTarget().getClass().getName();

        // 获取签名
        Signature signature = joinPoint.getSignature();
        // 获取切入的包名
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取即将执行的方法名
        String funcName = signature.getName();
        log.info("即将执行方法为: {}，属于{}包", funcName, declaringTypeName);

        // 也可以用来记录一些信息，比如获取请求的 URL 和 IP
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 获取请求 URL
        String url = request.getRequestURL().toString();
        // 获取请求 IP
        String ip = request.getRemoteAddr();
        log.info("用户请求的url为：{}，ip地址为：{}", url, ip);

    }

    @After("logAdvicePointcut()")
    public void doAfter(JoinPoint joinPoint) {

        log.info("==== doAfter 方法进入了====");
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        log.info("方法{}已经执行完", method);
    }

    /**
     * 在 @AfterReturning 注解 中，属性 returning 的值必须要和参数保持一致，否则会检测不到。
     * 该方法中的第二个入参就是被切方法的返回值，在 doAfterReturning 方法中可以对返回值进行增强
     * ，可以根据业务需要做相应的封装
     *
     * @param joinPoint
     * @param result
     * @return
     */
    @AfterReturning(pointcut = "logAdvicePointcut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {

        Signature signature = joinPoint.getSignature();
        String classMethod = signature.getName();
        log.info("方法{}执行完毕，返回参数为：{}", classMethod, result);
        // 实际项目中可以根据业务做具体的返回值增强
        log.info("对返回参数进行业务上的增强：{}", result + "增强版");
    }

    @AfterThrowing(pointcut = "logAdvicePointcut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        Signature signature = joinPoint.getSignature();
        String method = signature.getName();
        // 处理异常的逻辑
        log.info("执行方法{}出错，异常为：{}", method, ex.toString());
    }
}
