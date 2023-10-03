package ru.neoflex.models.validation;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "validation_rules")
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationRules {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private ValidationSource source;
    @Column(name = "is_bank_id_required")
    @NotNull(message = "Validation rule for isBankIdRequired is not specified")
    private Boolean isBankIdRequired;
    @Column(name = "is_last_name_required")
    @NotNull(message = "Validation rule for isLastNameRequired is not specified")
    private Boolean isLastNameRequired;
    @Column(name = "is_first_name_required")
    @NotNull(message = "Validation rule for isFirstNameRequired is not specified")
    private Boolean isFirstNameRequired;
    @Column(name = "is_middle_name_required")
    @NotNull(message = "Validation rule for isMiddleNameRequired is not specified")
    private Boolean isMiddleNameRequired;
    @Column(name = "is_birthdate_required")
    @NotNull(message = "Validation rule for isBirthdateRequired is not specified")
    private Boolean isBirthdateRequired;
    @Column(name = "is_passport_required")
    @NotNull(message = "Validation rule for isPassportRequired is not specified")
    private Boolean isPassportRequired;
    @Column(name = "is_place_of_birth_required")
    @NotNull(message = "Validation rule for isPlaceOfBirthRequired is not specified")
    private Boolean isPlaceOfBirthRequired;
    @Column(name = "is_phone_required")
    @NotNull(message = "Validation rule for isPhoneRequired is not specified")
    private Boolean isPhoneRequired;
    @Column(name = "is_email_required")
    @NotNull(message = "Validation rule for isEmailRequired is not specified")
    private Boolean isEmailRequired;
    @Column(name = "is_residential_address_required")
    @NotNull(message = "Validation rule for isResidentialAddressRequired is not specified")
    private Boolean isResidentialAddressRequired;
    @Column(name = "is_registration_address_required")
    @NotNull(message = "Validation rule for isRegistrationAddressRequired is not specified")
    private Boolean isRegistrationAddressRequired;
}
