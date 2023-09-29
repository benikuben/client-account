package ru.neoflex.dtos;

import lombok.Data;

@Data
public class SearchFilter {
    private String lastName;
    private String firstName;
    private String middleName;
    private String phone;
    private String email;
}
