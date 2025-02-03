package dev.project.mapper;

import dev.project.dto.AccountDTO;
import dev.project.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // определяем нужный контейнер
public interface AccountMapper {

    AccountDTO convertAccountDto(Account account);

    List<AccountDTO> convertAccountDtoList(List<Account> accountList);


    @Mapping(target = "client", ignore = true)
    Account convertToModel(AccountDTO accountDTO);

}
