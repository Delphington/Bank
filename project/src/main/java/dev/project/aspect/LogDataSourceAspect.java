package dev.project.aspect;

import dev.project.service.DataSourceErrorLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
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
    public void logErrors(JoinPoint joinPoint, Exception exception){
        log.error("An error occurred while processing {}", joinPoint.getSignature().getName());
        try {
            dataSourceErrorLogService.sendError(exception, joinPoint.getSignature().toShortString());
        } catch (Exception exc) {
            log.error("An error occurred while trying to send event to kafka {}", joinPoint.getSignature().getName());
            dataSourceErrorLogService.saveErrorInfo(exc, joinPoint.getSignature().toShortString());
        }
    }
}
