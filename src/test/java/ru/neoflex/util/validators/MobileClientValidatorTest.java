package ru.neoflex.util.validators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.Source;
import ru.neoflex.exceptions.ClientValidationException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MobileClientValidatorTest {
    @Autowired
    private MobileClientValidator mobileClientValidator;

    @Test
    void validate() {
        Client client = Client.builder().build();
        assertThrows(ClientValidationException.class, () -> mobileClientValidator.validate(client));
    }

    @Test
    void getSourceType() {
        Source expected = Source.MOBILE;
        Source actual = mobileClientValidator.getSourceType();
        assertEquals(actual, expected);
    }
}