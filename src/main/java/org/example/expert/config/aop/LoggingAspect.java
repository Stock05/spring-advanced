package org.example.expert.config.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* org.example.expert.domain..controller.*AdminController.*(..))")
    private void adminController() {}

    @Around("adminController()")
    public Object logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
           request = attributes.getRequest();
        }

        Long userId = (Long) request.getAttribute("userId");
        LocalDateTime now = LocalDateTime.now();
        String requestURI =  request.getRequestURI();

        // 메서드 실행전
        log.info("DateTime = {}, userId = {}, requestURI = {}", now,userId, requestURI);

        // joinPoint.proceed(); 호출 만으로 메서드가 실행됨
        Object result = joinPoint.proceed();

        //메서드 실행 후
        log.info("DateTime = {}, userId = {}, requestURI = {}, result = {}", now,userId, requestURI,result);



        return joinPoint.proceed();

    }

    }
