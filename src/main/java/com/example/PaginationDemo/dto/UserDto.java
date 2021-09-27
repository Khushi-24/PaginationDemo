package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "Please enter username.")
    @Size(min = 5, message = "Minimum 5 characters are required.")
    private String userName;

    @NotNull(message = "Password can't be null")
    @Size(min =5, "Password is too short, minimum requirement is of 5 characters")
    private String password;
}
