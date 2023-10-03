package ru.neoflex.services;

import ru.neoflex.models.validation.ValidationSource;

public interface ValidationSourceService {
    ValidationSource findByName(String name);
}
