package ru.neoflex.account.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.account.exceptions.NotFoundException;
import ru.neoflex.account.models.validation.ValidationSource;
import ru.neoflex.account.repositories.validation.ValidationSourceRepository;
import ru.neoflex.account.services.ValidationSourceService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidationSourceServiceImpl implements ValidationSourceService {
    private final ValidationSourceRepository validationSourceRepository;

    public ValidationSource findByName(String name) {
        return validationSourceRepository.findByName(name).orElseThrow(() -> new NotFoundException("Source " + name + " not found"));
    }
}
