package ru.neoflex.util.validators;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.neoflex.dtos.Source;
import ru.neoflex.exceptions.ClientAccountException;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class ClientValidatorFactory {
    private final Set<ClientValidator> validators;

    public ClientValidator getClientValidator(Source source) {
        return validators.stream()
                .filter(validator -> validator.getSourceType() == source)
                .findFirst()
                .orElseThrow(() -> new ClientAccountException(source + " unhandled source enum constant"));
    }
}
