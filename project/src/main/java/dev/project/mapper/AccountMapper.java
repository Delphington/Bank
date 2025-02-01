package dev.project.mapper;

import dev.project.dto.AccountDTO;
import dev.project.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring") // определяем нужный контейнер
public interface AccountMapper {

    AccountDTO convertAccountDto(Account account);

    List<AccountDTO> convertAccountDtoList(List<Account> accountList);


}
