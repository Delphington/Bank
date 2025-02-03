package dev.project.mapper;

import dev.project.dto.AccountDto;
import dev.project.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring") // определяем нужный контейнер
public interface AccountMapper {

    AccountDto convertAccountDto(Account account);

    List<AccountDto> convertAccountDtoList(List<Account> accountList);


    @Mapping(target = "client", ignore = true)
    Account convertToEntity(AccountDto accountDTO);

}
