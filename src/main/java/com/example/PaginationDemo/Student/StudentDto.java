package com.example.PaginationDemo.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {

    private Long studentId;

    @NotNull(message = "Student name can't be null.")
    @NotBlank(message = "Student name can't be blank.")
    @Size(min = 3, message = "Student name should have at least 3 character. ")
    private String studentName;

    @NotNull(message = "Student division can't be null.")
    @NotBlank(message = "Student division can't be blank.")
    private String studentDivision;

    @NotNull(message = "Student age can't be null.")
    @NotBlank(message = "Student age can't be blank.")
    private Integer studentAge;

}
