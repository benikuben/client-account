package ru.neoflex.account.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import ru.neoflex.account.dtos.SearchFilter;
import ru.neoflex.account.exceptions.ClientAccountException;
import ru.neoflex.account.exceptions.NotFoundException;
import ru.neoflex.account.models.client.Client;
import ru.neoflex.account.repositories.client.ClientRepository;
import ru.neoflex.account.services.impl.ClientServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    void save() {
        Client client = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .email("ivan@mail.ru")
                .registrationAddress("Moscow")
                .residentialAddress("Moscow")
                .build();

        Client expectedClient = new Client();
        BeanUtils.copyProperties(client, expectedClient);
        expectedClient.setId(1L);

        when(clientRepository.save(any())).thenReturn(expectedClient);

        Client actualClient = clientService.save(client);

        assertEquals(expectedClient, actualClient);
    }

    @Test
    void findById() {
        Long id = 1L;

        Client expectedClient = Client.builder().id(id).build();

        when(clientRepository.findById(any()))
                .thenReturn(Optional.of(expectedClient), Optional.empty());

        Client actualClient = clientService.findById(id);

        assertEquals(actualClient, expectedClient);
        assertThrows(NotFoundException.class, () -> clientService.findById(2L));
    }

    @Test
    void search() {
        SearchFilter filters = new SearchFilter();
        assertThrows(ClientAccountException.class, () -> clientService.search(filters));
    }
}