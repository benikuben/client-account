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
public class MailClientValidator implements ClientValidator{
    @Override
    public void validate(Client client) {
        List<String> message = new ArrayList<>();

        if (StringUtils.isBlank(client.getFirstName())) {
            message.add("Required parameter 'first name' is omitted");
        }
        if (StringUtils.isBlank(client.getEmail())) {
            message.add("Required parameter 'email' is omitted");
        }

        if (message.size() > 0) {
            throw new ClientValidationException(message.toString());
        }
    }

    @Override
    public Source getSourceType() {
        return Source.MAIL;
    }
}
