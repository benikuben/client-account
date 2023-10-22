package ru.neoflex.account.repositories.validation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.account.models.validation.ValidationSource;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValidationSourceRepository extends JpaRepository<ValidationSource, Long> {
    Optional<ValidationSource> findByName(String name);

    List<ValidationSource> findAll();
}
