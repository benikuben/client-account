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
class GosuslugiClientValidatorTest {
    @Autowired
    private GosuslugiClientValidator gosuslugiClientValidator;

    @Test
    void validate() {
        Client client1 = Client.builder()
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client1));

        Client client2 = Client.builder()
                .bankId(1L)
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client2));

        Client client3 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client3));

        Client client4 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client4));

        Client client5 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client5));

        Client client6 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client6));

        Client client7 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .phone("79999999999")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client7));

        Client client8 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .registrationAddress("Moscow")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client8));

        Client client9 = Client.builder()
                .bankId(1L)
                .lastName("Ivanov")
                .firstName("Ivan")
                .middleName("Ivanovich")
                .birthdate(LocalDate.of(2000, 1, 1))
                .passport("1111 111111")
                .placeOfBirth("Moscow")
                .phone("79999999999")
                .build();
        assertThrows(ClientValidationException.class, () -> gosuslugiClientValidator.validate(client9));
    }

    @Test
    void getSourceType() {
        Source expected = Source.GOSUSLUGI;
        Source actual = gosuslugiClientValidator.getSourceType();
        assertEquals(actual, expected);
    }
}