package ru.neoflex.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.neoflex.models.Client;

public interface ClientRepository extends JpaRepository<Client, Long>, QuerydslPredicateExecutor<Client> {
    Client save(Client client);
}
