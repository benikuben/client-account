package ru.neoflex.util.validators;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.Source;
import ru.neoflex.exceptions.ClientValidationException;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GosuslugiClientValidator implements ClientValidator {

    @Override
    public void validate(Client client) {
        List<String> message = new ArrayList<>();

        if (client.getBankId()==null) {
            message.add("Required parameter 'bank_id' is omitted");
        }
        if (StringUtils.isBlank(client.getLastName())) {
            message.add("Required parameter 'last_name' is omitted");
        }
        if (StringUtils.isBlank(client.getFirstName())) {
            message.add("Required parameter 'first_name' is omitted");
        }
        if (StringUtils.isBlank(client.getMiddleName())) {
            message.add("Required parameter 'middle_name' is omitted");
        }
        if (client.getBirthdate()==null) {
            message.add("Required parameter 'birthdate' is omitted");
        }
        if (StringUtils.isBlank(client.getPassport())) {
            message.add("Required parameter 'passport' is omitted");
        }
        if (StringUtils.isBlank(client.getPlaceOfBirth())) {
            message.add("Required parameter 'place_of_birth' is omitted");
        }
        if (StringUtils.isBlank(client.getPhone())) {
            message.add("Required parameter 'phone' is omitted");
        }
        if (StringUtils.isBlank(client.getRegistrationAddress())) {
            message.add("Required parameter 'registration_address' is omitted");
        }

        if (message.size() > 0) {
            throw new ClientValidationException(message.toString());
        }
    }

    @Override
    public Source getSourceType() {
        return Source.GOSUSLUGI;
    }
}
