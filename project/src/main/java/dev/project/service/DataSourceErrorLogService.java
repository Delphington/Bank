package dev.project.service;

import dev.project.dto.ErrorDto;
import dev.project.entity.DataSourceErrorLog;
import dev.project.mapper.DataSourceErrorMapper;
import dev.project.repository.DataSourceErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static dev.project.aspect.MetricAspect.getKafkaKey;
import static dev.project.util.ConstantsUtil.METRICS;

@RequiredArgsConstructor
@Service
public class DataSourceErrorLogService {
    private final DataSourceErrorLogRepository repository;

    private final DataSourceErrorMapper errorMapper;

    private final KafkaTemplate<String, ErrorDto> kafkaTemplate;


    @Transactional
    public void saveErrorInfo(Exception e, String methodSignature) {
        DataSourceErrorLog error = getDataSourceErrorLog(e, methodSignature);
        repository.save(error);
    }

    public void sendError(Exception e, String methodSignature) {
        DataSourceErrorLog error = getDataSourceErrorLog(e, methodSignature);
        List<Header> headers = new ArrayList<>();
        headers.add(new RecordHeader("error_type", "DATA_SOURCE".getBytes(StandardCharsets.UTF_8)));
        String key = getKafkaKey();
        ProducerRecord<String, ErrorDto> record = new ProducerRecord<>(METRICS, 1, key, errorMapper.toDto(error), headers);
        kafkaTemplate.send(record);
    }

    private DataSourceErrorLog getDataSourceErrorLog(Exception e, String methodSignature) {
        return DataSourceErrorLog.builder()
                .stackTraceMessage(ExceptionUtils.getStackTrace(e))
                .message(e.getMessage())
                .signatureMethod(methodSignature)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
