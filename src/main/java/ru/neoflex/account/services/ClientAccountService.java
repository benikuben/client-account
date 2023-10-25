package ru.neoflex.account.services;

import ru.neoflex.account.dtos.Client;
import ru.neoflex.account.dtos.SearchFilter;

import java.util.List;

public interface ClientAccountService {
    void create(Client clientDto, String source);

    Client findById(Long id);

    List<Client> search(SearchFilter filters);
}
