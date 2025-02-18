package dev.project.consumer;

import dev.project.dto.AccountDto;
import dev.project.entity.Account;
import dev.project.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.CodePointLength;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static dev.project.util.ConstantsUtil.ACCOUNTS;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountConsumer {
    private final AccountService accountService;

    @KafkaListener(topics = ACCOUNTS, groupId = "t1-intensive", containerFactory = "accountFactory")
    void addAccount(AccountDto dto) {
        log.info("Получаем запрос в AccountConsumer {}", dto);
        accountService.createAccount(dto, dto.getId());
    }
}
