package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CourseStudentRequestDto {

    @NotNull(message = "Course Id can't be null")
    @NotBlank(message = "Please enter course Id.")
    private Long courseId;

    @NotNull(message = "Student Id can't be null")
    @NotBlank(message = "Please enter student Id.")
    private Long studentId;
}
