package dev.project.mapper;

import dev.project.dto.TransactionDto;
import dev.project.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDto convertToDto(Transaction transaction);

    List<TransactionDto> convertToListDto(List<Transaction> list);

    @Mapping(target = "account", ignore = true)
    Transaction convertToEntity(TransactionDto transactionDTO);
}
