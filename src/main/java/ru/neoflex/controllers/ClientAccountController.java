package ru.neoflex.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.SearchFilter;
import ru.neoflex.services.ClientAccountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
@Validated
public class ClientAccountController {
    private final ClientAccountService clientAccountService;

    @PostMapping()
    public ResponseEntity<Void> create(@RequestBody @Valid Client client, @RequestHeader("x-Source") String source) {
        clientAccountService.create(client, source);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(clientAccountService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Client>> search(SearchFilter filters) {
        return new ResponseEntity<>(clientAccountService.search(filters), HttpStatus.OK);
    }
}
