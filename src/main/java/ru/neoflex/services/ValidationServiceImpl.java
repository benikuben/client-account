package ru.neoflex.services;

import jakarta.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import ru.neoflex.dtos.Client;
import ru.neoflex.exceptions.ClientValidationException;
import ru.neoflex.models.validation.ValidationRules;
import ru.neoflex.models.validation.ValidationSource;

import java.util.ArrayList;
import java.util.List;

@Service
@Validated
public class ValidationServiceImpl implements ValidationService {
    @Override
    public void validateRequiredParameters(@Valid ValidationSource validationSource, Client clientDto) {
        ValidationRules validationRules = validationSource.getValidationRules();

        List<String> message = new ArrayList<>();
        if (validationRules.getIsBankIdRequired())
            if (clientDto.getBankId() == null)
                message.add("Required parameter 'bank_id' is omitted");
        if (validationRules.getIsLastNameRequired())
            if (StringUtils.isBlank(clientDto.getLastName()))
                message.add("Required parameter 'last_name' is omitted");
        if (validationRules.getIsFirstNameRequired())
            if (StringUtils.isBlank(clientDto.getFirstName()))
                message.add("Required parameter 'first_name' is omitted");
        if (validationRules.getIsMiddleNameRequired())
            if (StringUtils.isBlank(clientDto.getMiddleName()))
                message.add("Required parameter 'middle_name' is omitted");
        if (validationRules.getIsBirthdateRequired())
            if (clientDto.getBirthdate() == null)
                message.add("Required parameter 'birthdate' is omitted");
        if (validationRules.getIsPassportRequired())
            if (StringUtils.isBlank(clientDto.getPassport()))
                message.add("Required parameter 'passport' is omitted");
        if (validationRules.getIsPlaceOfBirthRequired())
            if (StringUtils.isBlank(clientDto.getPlaceOfBirth()))
                message.add("Required parameter 'place_of_birth' is omitted");
        if (validationRules.getIsPhoneRequired())
            if (StringUtils.isBlank(clientDto.getPhone()))
                message.add("Required parameter 'phone' is omitted");
        if (validationRules.getIsEmailRequired())
            if (StringUtils.isBlank(clientDto.getEmail()))
                message.add("Required parameter 'email' is omitted");
        if (validationRules.getIsResidentialAddressRequired())
            if (StringUtils.isBlank(clientDto.getResidentialAddress()))
                message.add("Required parameter 'residential_address' is omitted");
        if (validationRules.getIsRegistrationAddressRequired())
            if (StringUtils.isBlank(clientDto.getRegistrationAddress()))
                message.add("Required parameter 'registration_address' is omitted");

        if (message.size() > 0) {
            throw new ClientValidationException(message.toString());
        }
    }
}
