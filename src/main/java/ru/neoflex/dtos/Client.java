package ru.neoflex.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class Client {
    private Long bankId;
    @Pattern(regexp = "^.{0}|([A-Za-z]{2,30})$", message = "Last name must contain from 2 to 30 Latin letters")
    private String lastName;
    @Pattern(regexp = "^.{0}|([A-Za-z]{2,30})$", message = "First name must contain from 2 to 30 Latin letters")
    private String firstName;
    @Pattern(regexp = "^.{0}|([A-Za-z]{2,30})$", message = "Middle name must contain from 2 to 30 Latin letters")
    private String middleName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;
    @Pattern(regexp = "^.{0}|([0-9]{4} [0-9]{6})$", message = "Passport series and number must be in the format XXXX XXXXXX")
    private String passport;
    private String placeOfBirth;
    @Pattern(regexp = "^.{0}|(7[0-9]{10})$", message = "Phone number must be in the format 7XXXXXXXXXX")
    private String phone;
    @Email(message = "Email must be correct")
    private String email;
    private String residentialAddress;
    private String registrationAddress;
}
