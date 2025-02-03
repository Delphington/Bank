package dev.project.service;

import dev.project.dto.ClientDTO;
import dev.project.exception.NotFoundDataException;
import dev.project.mapper.ClientMapper;
import dev.project.model.Client;
import dev.project.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        return clientMapper.convertClientDto(clientRepository.findById(id).orElseThrow(() ->
                new NotFoundDataException("Client was not found, where id = " + id)));
    }
}
