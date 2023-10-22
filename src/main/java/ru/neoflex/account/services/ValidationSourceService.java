package ru.neoflex.account.services;

import ru.neoflex.account.models.validation.ValidationSource;

public interface ValidationSourceService {
    ValidationSource findByName(String name);
}
