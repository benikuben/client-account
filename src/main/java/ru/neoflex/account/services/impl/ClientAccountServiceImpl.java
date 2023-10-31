package ru.neoflex.account.services.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.neoflex.account.dtos.Client;
import ru.neoflex.account.dtos.SearchFilter;
import ru.neoflex.account.models.validation.ValidationSource;
import ru.neoflex.account.services.ClientAccountService;
import ru.neoflex.account.services.ClientService;
import ru.neoflex.account.services.ValidationService;
import ru.neoflex.account.services.ValidationSourceService;
import ru.neoflex.account.util.mappers.ClientMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Validated
@Slf4j
public class ClientAccountServiceImpl implements ClientAccountService {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final ValidationService validationService;
    private final ValidationSourceService validationSourceService;

    @Override
    public void create(Client clientDto, String source) {
        log.info("Client to validate {} from source {}", clientDto, source);
        @Valid ValidationSource validationSource = validationSourceService.findByName(source);
        validationService.validateRequiredParameters(validationSource, clientDto);
        log.info("Client successfully validated {}", clientDto);
        ru.neoflex.account.models.client.Client client = clientMapper.clientDtoToClient(clientDto);
        clientService.save(client);
        log.info("Client was saved in database {}", client);
    }

    @Override
    public Client findById(Long id) {
        log.info("Find client by id {}", id);
        ru.neoflex.account.models.client.Client client = clientService.findById(id);
        Client clientDto = clientMapper.clientToClientDto(client);
        log.info("Found client {}", clientDto);
        return clientDto;
    }

    @Override
    public List<Client> search(SearchFilter filters) {
        log.info("Search by filters {}", filters);
        List<ru.neoflex.account.models.client.Client> clients = clientService.search(filters);
        List<Client> clientsDto = clients.stream().map(clientMapper::clientToClientDto).toList();
        log.info("Search result {}", clientsDto);
        return clientsDto;
    }
}
