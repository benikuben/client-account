package ru.neoflex.account.repositories.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.neoflex.account.models.client.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, QuerydslPredicateExecutor<Client> {
    Client save(Client client);
}
