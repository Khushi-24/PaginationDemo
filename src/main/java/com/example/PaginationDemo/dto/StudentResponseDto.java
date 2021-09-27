package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentResponseDto {

    @NotNull(message = "Student Id can't be null")
    @NotBlank(message = "Please enter student Id.")
    private Long studentId;

    @NotNull(message = "Student name can't be null.")
    @NotBlank(message = "Student name can't be blank.")
    @Size(min = 3, message = "Student name should have at least 3 character. ")
    private String studentName;
}
