package ru.neoflex.account.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.neoflex.account.exceptions.NotFoundException;
import ru.neoflex.account.models.validation.ValidationSource;
import ru.neoflex.account.repositories.validation.ValidationSourceRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationSourceServiceTest {
    @Mock
    private ValidationSourceRepository validationSourceRepository;

    @InjectMocks
    private ValidationSourceServiceImpl validationSourceService;

    @Test
    void findByName() {
        String name = "mail";

        ValidationSource expectedValidationSource = ValidationSource.builder().name(name).build();

        when(validationSourceRepository.findByName(any()))
                .thenReturn(Optional.of(expectedValidationSource), Optional.empty());

        ValidationSource actualValidationSource = validationSourceService.findByName(name);

        assertEquals(actualValidationSource, expectedValidationSource);
        assertThrows(NotFoundException.class, () -> validationSourceService.findByName("phone"));
    }
}