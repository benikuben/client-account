package ru.neoflex.util.validators;

import ru.neoflex.dtos.Client;
import ru.neoflex.dtos.Source;

public interface ClientValidator {
    void validate(Client client);
    Source getSourceType();
}
