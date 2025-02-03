package dev.project.mapper;

import dev.project.dto.TransactionDTO;
import dev.project.model.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    TransactionDTO convertToDto(Transaction transaction);

    List<TransactionDTO> convertToListDto(List<Transaction> list);

    @Mapping(target = "account", ignore = true)
    Transaction convertToModel(TransactionDTO transactionDTO);
}
