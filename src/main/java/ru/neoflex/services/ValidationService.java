package ru.neoflex.services;

import jakarta.validation.Valid;
import ru.neoflex.dtos.Client;
import ru.neoflex.models.validation.ValidationSource;

public interface ValidationService {
    void validateRequiredParameters(@Valid ValidationSource validationSource, Client clientDto);
}
