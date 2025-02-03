package dev.project.service;

import dev.project.entity.DataSourceErrorLog;
import dev.project.repository.DataSourceErrorLogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DataSourceErrorLogService {
    private final DataSourceErrorLogRepository dataSourceErrorLogRepository;

    @Transactional
    public void saveErrorMessage(Exception e, String methodSignature) {

        DataSourceErrorLog dataSourceErrorLog =
                DataSourceErrorLog
                        .builder()
                        .stackTraceMessage(ExceptionUtils.getMessage(e))
                        .signatureMethod(methodSignature)
                        .message(e.getMessage())
                        .build();
        dataSourceErrorLogRepository.save(dataSourceErrorLog);
    }
}
