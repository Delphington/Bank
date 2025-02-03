package dev.project.aspect;

import dev.project.repository.DataSourceErrorLogRepository;
import dev.project.service.DataSourceErrorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Aspect
@Slf4j
@RequiredArgsConstructor
public class LogDataSourceAspect {

    private final DataSourceErrorLogService dataSourceErrorLogService;

    @AfterThrowing(
            pointcut = "@annotation(dev.project.annotation.LogDataSourceError)",
            throwing = "exception")
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logErrors(JoinPoint joinpoint, Exception exception){
        log.error("ERROR WITH DATABASE:  " + joinpoint.getSignature().getName());
        dataSourceErrorLogService.saveErrorMessage(exception, joinpoint.getSignature().toShortString());
    }
}
