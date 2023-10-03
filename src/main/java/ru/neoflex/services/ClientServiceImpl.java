package ru.neoflex.services;

import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.dtos.SearchFilter;
import ru.neoflex.exceptions.ClientAccountException;
import ru.neoflex.exceptions.NotFoundException;
import ru.neoflex.models.client.Client;
import ru.neoflex.repositories.client.ClientRepository;
import ru.neoflex.util.QPredicates;

import java.util.List;

import static ru.neoflex.models.client.QClient.client;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new NotFoundException("Client with id " + id + " not found"));
    }

    @Override
    public List<Client> search(SearchFilter filters) {
        Predicate predicates = QPredicates.builder()
                .add(filters.getLastName(), client.lastName::containsIgnoreCase)
                .add(filters.getFirstName(), client.firstName::containsIgnoreCase)
                .add(filters.getMiddleName(), client.middleName::containsIgnoreCase)
                .add(filters.getPhone(), client.phone::containsIgnoreCase)
                .add(filters.getEmail(), client.email::containsIgnoreCase)
                .buildAnd();
        if (predicates == null) {
            throw new ClientAccountException("At least one search field must be specified");
        }
        return IterableUtils.toList(clientRepository.findAll(predicates));
    }
}
