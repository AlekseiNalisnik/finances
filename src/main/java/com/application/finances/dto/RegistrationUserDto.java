package com.application.finances.dto;

import lombok.Data;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}
