package ru.neoflex.account.services;

import jakarta.validation.Valid;
import ru.neoflex.account.dtos.Client;
import ru.neoflex.account.models.validation.ValidationSource;

public interface ValidationService {
    void validateRequiredParameters(@Valid ValidationSource validationSource, Client clientDto);
}
