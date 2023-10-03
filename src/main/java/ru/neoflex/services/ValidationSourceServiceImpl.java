package ru.neoflex.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.neoflex.exceptions.NotFoundException;
import ru.neoflex.models.validation.ValidationSource;
import ru.neoflex.repositories.validation.ValidationSourceRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ValidationSourceServiceImpl implements ValidationSourceService {
    private final ValidationSourceRepository validationSourceRepository;

    public ValidationSource findByName(String name) {
        return validationSourceRepository.findByName(name).orElseThrow(() -> new NotFoundException("Source " + name + " not found"));
    }
}
