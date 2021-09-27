package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserResponseDto {

    @NotNull(message = "Please enter username.")
    @Size(min = 5, message = "Minimum 5 characters are required.")
    private String userName;
}
