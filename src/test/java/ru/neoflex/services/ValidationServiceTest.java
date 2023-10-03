package ru.neoflex.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.neoflex.dtos.Client;
import ru.neoflex.exceptions.ClientValidationException;
import ru.neoflex.models.validation.ValidationRules;
import ru.neoflex.models.validation.ValidationSource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ValidationServiceTest {
    @Autowired
    private ValidationService validationService;

    @Test
    void validateRequiredParameters() {
        ValidationRules validationRules = ValidationRules.builder()
                .isBankIdRequired(false)
                .isLastNameRequired(false)
                .isFirstNameRequired(true)
                .isMiddleNameRequired(false)
                .isBirthdateRequired(false)
                .isPassportRequired(false)
                .isPlaceOfBirthRequired(false)
                .isPhoneRequired(false)
                .isEmailRequired(true)
                .isResidentialAddressRequired(false)
                .isRegistrationAddressRequired(false)
                .build();
        ValidationSource validationSource = ValidationSource.builder()
                .validationRules(validationRules)
                .build();

        Client client1 = Client.builder()
                .email("ivan@mail.ru")
                .build();
        assertThrows(ClientValidationException.class, () -> validationService.validateRequiredParameters(validationSource, client1));

        Client client2 = Client.builder()
                .firstName("Ivan")
                .build();
        assertThrows(ClientValidationException.class, () -> validationService.validateRequiredParameters(validationSource, client2));
    }
}