package ru.neoflex.account.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SearchFilter {
    @Schema(
            description = "last name",
            name = "last name",
            example = "Ivanov"
    )
    private String lastName;
    @Schema(
            description = "first name",
            name = "first name",
            example = "Ivan"
    )
    private String firstName;
    @Schema(
            description = "middle name",
            name = "middle name",
            example = "Ivanovich"
    )
    private String middleName;
    @Schema(
            description = "phone",
            name = "phone",
            example = "71234567890"
    )
    private String phone;
    @Schema(
            description = "email",
            name = "email",
            example = "ivan@mail.ru"
    )
    private String email;
}
