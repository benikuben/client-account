package ru.neoflex.util.validators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.Source;
import ru.neoflex.exceptions.ClientValidationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailClientValidatorTest {
    @Autowired
    private MailClientValidator mailClientValidator;

    @Test
    void validate() {
        Client client1 = Client.builder()
                .email("ivan@mail.ru")
                .build();
        assertThrows(ClientValidationException.class, () -> mailClientValidator.validate(client1));

        Client client2 = Client.builder()
                .firstName("Ivan")
                .build();
        assertThrows(ClientValidationException.class, () -> mailClientValidator.validate(client2));
    }

    @Test
    void getSourceType() {
        Source expected = Source.MAIL;
        Source actual = mailClientValidator.getSourceType();
        assertEquals(actual, expected);
    }
}