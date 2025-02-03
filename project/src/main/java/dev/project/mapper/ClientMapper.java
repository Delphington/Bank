package dev.project.mapper;


import dev.project.dto.ClientDTO;
import dev.project.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO convertClientDto(Client client);

    //@Mapping()
    Client convertModel(ClientDTO clientDTO);




}
