package ru.neoflex.util.validators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.Source;
import ru.neoflex.exceptions.ClientValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BankClientValidatorTest {
    @Autowired
    private BankClientValidator bankClientValidator;

    @Test
    void validate() {
        Client client1 = Client.builder()
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .build();
        assertThrows(ClientValidationException.class, () -> bankClientValidator.validate(client1));

        Client client2 = Client.builder()
                .bankId(1L)
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .build();
        assertThrows(ClientValidationException.class, () -> bankClientValidator.validate(client2));

        Client client3 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .build();
        assertThrows(ClientValidationException.class, () -> bankClientValidator.validate(client3));

        Client client4 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .build();
        assertThrows(ClientValidationException.class, () -> bankClientValidator.validate(client4));

        Client client5 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .passport("1111 111111")
                .build();
        assertThrows(ClientValidationException.class, () -> bankClientValidator.validate(client5));

        Client client6 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .build();
        assertThrows(ClientValidationException.class, () -> bankClientValidator.validate(client6));
    }

    @Test
    void getSourceType() {
        Source expected = Source.BANK;
        Source actual = bankClientValidator.getSourceType();
        assertEquals(actual, expected);
    }
}