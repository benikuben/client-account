package ru.neoflex.util.validators;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.dtos.Source;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientValidatorFactoryTest {
    @Autowired
    private ClientValidatorFactory clientValidatorFactory;

    @Test
    void getClientValidator() {
        ClientValidator actual = clientValidatorFactory.getClientValidator(Source.MAIL);
        assertInstanceOf(MailClientValidator.class, actual);

        actual= clientValidatorFactory.getClientValidator(Source.MOBILE);
        assertInstanceOf(MobileClientValidator.class, actual);

        actual= clientValidatorFactory.getClientValidator(Source.BANK);
        assertInstanceOf(BankClientValidator.class, actual);

        actual= clientValidatorFactory.getClientValidator(Source.GOSUSLUGI);
        assertInstanceOf(GosuslugiClientValidator.class, actual);
    }
}