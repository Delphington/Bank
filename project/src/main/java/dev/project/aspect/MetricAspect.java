package dev.project.aspect;


import dev.project.annotation.Metric;
import dev.project.dto.ErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import static dev.project.util.ConstantsUtil.METRICS;

//Аспект для измеренения выполнения метода
@Aspect
@Async
@Log4j2
@Component
@RequiredArgsConstructor
public class MetricAspect {

    private static final AtomicLong START_TIME = new AtomicLong();

    //Ключ -> ошибка
    private final KafkaTemplate<String, ErrorDto> kafkaTemplate;

    @Before("@annotation(dev.project.annotation.Metric)")
    public void logExecTime(JoinPoint joinPoint) {
        log.info("Старт метода: {}", joinPoint.getSignature().toShortString());
        START_TIME.addAndGet(System.currentTimeMillis());
    }


    @After("@annotation(metric)")
    public void calculateTime(JoinPoint joinPoint, Metric metric) {
        long afterTime = System.currentTimeMillis();
        logExecutionTime(afterTime, START_TIME.get());
        long resultTime = afterTime - START_TIME.get();
        log.info("ResultTime = {} and limit is {}", resultTime, metric.limit());
        if (resultTime > metric.limit()) {
            List<Header> headers = new ArrayList<>(); //Список заголовков для отправку в kafka
            headers.add(new RecordHeader("error_type", "METRICS".getBytes(StandardCharsets.UTF_8)));
            String key = getKafkaKey();
            ErrorDto error = new ErrorDto();
            error.setMessage("Execution time: " + resultTime + ". Method arguments: " + Arrays.toString(joinPoint.getArgs()));
            error.setMethodSignature(joinPoint.getSignature().toShortString());
            error.setCreatedOn(LocalDateTime.now());
            ProducerRecord<String, ErrorDto> record = new ProducerRecord<>(METRICS, 0, key, error, headers);
            kafkaTemplate.send(record);
        }
        START_TIME.set(0L);
    }

    @Around("@annotation(dev.project.annotation.Metric)")
    public Object logExecTime(ProceedingJoinPoint pJoinPoint) throws Throwable {
        log.info("Вызов метода: {}", pJoinPoint.getSignature().toShortString());
        long beforeTime = System.currentTimeMillis();
        Object result;
        try {
            result = pJoinPoint.proceed();
        } finally {
            long afterTime = System.currentTimeMillis();
            logExecutionTime(afterTime, beforeTime);
        }

        return result;
    }

    private void logExecutionTime(long afterTime, Long beforeTime) {
        log.info("Время исполнения: {} ms", (afterTime - beforeTime));
    }

    public static String getKafkaKey() {
        return UUID.randomUUID().toString();
    }

}
