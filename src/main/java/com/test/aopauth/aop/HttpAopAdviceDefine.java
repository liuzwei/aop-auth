package com.test.aopauth.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class HttpAopAdviceDefine {

//    @Pointcut("execution(public * com.test.aopauth.controller.*.*(..)) ")
    @Pointcut("@annotation(com.test.aopauth.aop.AuthChecker)")
    public void pointCut(){

    }

    /**
     * aop切面的执行顺序，aop->before->method->around->after->afterReturning
     * @param joinPoint
     * @return
     * @throws Throwable
     */

    @Around("pointCut()")
    public Object checkAuth(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String root = request.getParameter("root");
        if (root == null){
            return "auth error";
        }

        return joinPoint.proceed();
    }
}
