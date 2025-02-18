package dev.project.consumer;


import dev.project.dto.TransactionDto;
import dev.project.service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static dev.project.util.ConstantsUtil.TRANSACTIONS;

@Log4j2
@RequiredArgsConstructor
@Component
public class TransactionConsumer {

    private final TransactionService transactionService;

    @KafkaListener(topics = TRANSACTIONS, groupId = "t1-intensive", containerFactory = "transactionFactory")
    void addAccount(TransactionDto dto) {
        log.info("Получили информацию в TransactionConsumer {}", dto);
        transactionService.createTransaction(dto, dto.getId());
    }
}
