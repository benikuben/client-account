package ru.neoflex.account.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.account.dtos.Client;
import ru.neoflex.account.dtos.SearchFilter;
import ru.neoflex.account.services.ClientAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Validated
public class ClientAccountController implements ClientAccountAPI{
    private final ClientAccountService clientAccountService;

    public ResponseEntity<Void> create(Client client, String source) {
        clientAccountService.create(client, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Client> findById(Long id) {
        return new ResponseEntity<>(clientAccountService.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<List<Client>> search(SearchFilter filters) {
        return new ResponseEntity<>(clientAccountService.search(filters), HttpStatus.OK);
    }
}
