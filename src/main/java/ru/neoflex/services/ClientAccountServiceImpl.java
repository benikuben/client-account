package ru.neoflex.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.SearchFilter;
import ru.neoflex.dtos.Source;
import ru.neoflex.util.mappers.ClientMapper;
import ru.neoflex.util.validators.ClientValidatorFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientAccountServiceImpl implements ClientAccountService {
    private final ClientService clientService;
    private final ClientMapper clientMapper;
    private final ClientValidatorFactory clientValidatorFactory;

    @Override
    public void create(Client clientDto, Source source) {
        log.info("Client to validate {} from source {}", clientDto, source);
        clientValidatorFactory.getClientValidator(source).validate(clientDto);
        log.info("Client successfully validated {}", clientDto);
        ru.neoflex.models.Client client = clientMapper.clientDtoToClient(clientDto);
        clientService.save(client);
        log.info("Client was saved in database {}", client);
    }

    @Override
    public Client findById(Long id) {
        log.info("Find client by id {}", id);
        ru.neoflex.models.Client client = clientService.findById(id);
        Client clientDto = clientMapper.clientToClientDto(client);
        log.info("Found client {}", clientDto);
        return clientDto;
    }

    @Override
    public List<Client> search(SearchFilter filters) {
        log.info("Search by filters {}", filters);
        List<ru.neoflex.models.Client> clients = clientService.search(filters);
        List<Client> clientsDto = clients.stream().map(clientMapper::clientToClientDto).toList();
        log.info("Search result {}", clientsDto);
        return clientsDto;
    }
}
