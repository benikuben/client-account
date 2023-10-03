package ru.neoflex.services;

import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.SearchFilter;

import java.util.List;

public interface ClientAccountService {
    void create(Client clientDto, String source);

    Client findById(Long id);

    List<Client> search(SearchFilter filters);
}
