package ru.neoflex.account.models.validation;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Table(name = "validation_source")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "validationRules")
public class ValidationSource {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToOne(mappedBy = "source")
    @NotNull(message = "Validation rules are not specified")
    @Valid
    private ValidationRules validationRules;
}
