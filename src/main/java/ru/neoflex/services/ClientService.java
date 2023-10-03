package ru.neoflex.services;

import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.dtos.SearchFilter;
import ru.neoflex.models.client.Client;

import java.util.List;

public interface ClientService {
    @Transactional
    Client save(Client client);

    Client findById(Long id);

    List<Client> search(SearchFilter filters);
}
