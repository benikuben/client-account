package ru.neoflex.account.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class Client {
    @Schema(
            description = "bank id",
            name = "bank id",
            example = "1"
    )
    private Long bankId;
    @Pattern(regexp = "^.{0}|([A-Za-z]{2,30})$", message = "Last name must contain from 2 to 30 Latin letters")
    @Schema(
            description = "last name",
            name = "last name",
            example = "Ivanov"
    )
    private String lastName;
    @Pattern(regexp = "^.{0}|([A-Za-z]{2,30})$", message = "First name must contain from 2 to 30 Latin letters")
    @Schema(
            description = "first name",
            name = "first name",
            example = "Ivan"
    )
    private String firstName;
    @Pattern(regexp = "^.{0}|([A-Za-z]{2,30})$", message = "Middle name must contain from 2 to 30 Latin letters")
    @Schema(
            description = "middle name",
            name = "middle name",
            example = "Ivanovich"
    )
    private String middleName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Schema(
            description = "birthdate",
            name = "birthdate",
            example = "2000-01-01"
    )
    private LocalDate birthdate;
    @Pattern(regexp = "^.{0}|([0-9]{4} [0-9]{6})$", message = "Passport series and number must be in the format XXXX XXXXXX")
    @Schema(
            description = "passport",
            name = "passport",
            example = "1234 567890"
    )
    private String passport;
    @Schema(
            description = "place of birth",
            name = "place of birth",
            example = "Moscow"
    )
    private String placeOfBirth;
    @Pattern(regexp = "^.{0}|(7[0-9]{10})$", message = "Phone number must be in the format 7XXXXXXXXXX")
    @Schema(
            description = "phone",
            name = "phone",
            example = "71234567890"
    )
    private String phone;
    @Email(message = "Email must be correct")
    @Schema(
            description = "email",
            name = "email",
            example = "ivan@mail.ru"
    )
    private String email;
    @Schema(
            description = "residential address",
            name = "residential address",
            example = "Moscow"
    )
    private String residentialAddress;
    @Schema(
            description = "registration address",
            name = "registration address",
            example = "Moscow"
    )
    private String registrationAddress;
}
