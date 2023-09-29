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
public class MobileClientValidator implements ClientValidator {
    @Override
    public void validate(Client client) {
        List<String> message = new ArrayList<>();

        if (StringUtils.isBlank(client.getPhone())) {
            message.add("Required parameter 'phone' is omitted");
        }

        if (message.size() > 0) {
            throw new ClientValidationException(message.toString());
        }
    }

    @Override
    public Source getSourceType() {
        return Source.MOBILE;
    }

}
