package com.example.basic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RepositoryAspect {
    @After(value = "execution (* com.example.basic.repository.*.*(..))")
    public void onAfterHandler(JoinPoint joinPoint) {
        log.debug("@After run");
    }

    @AfterReturning(value = "execution (* com.example.basic.repository.*.*(..))", returning = "data")
    public void onAfterReturningHandler(JoinPoint joinPoint, Object data) {
        if (data != null) {
            log.debug(data.toString());
        }
        log.debug("Repository @AfterReturning run");
    }

}
