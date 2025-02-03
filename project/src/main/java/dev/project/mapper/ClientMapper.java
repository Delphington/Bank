package dev.project.mapper;


import dev.project.dto.ClientDto;
import dev.project.entity.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDto convertClientDto(Client client);

    Client convertToEntity(ClientDto clientDTO);
}
