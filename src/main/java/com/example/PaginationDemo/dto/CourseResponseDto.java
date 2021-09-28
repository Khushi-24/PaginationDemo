package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CourseResponseDto {

    @NotNull(message = "Course id can't be null.")
    private Long courseId;


    @NotNull(message = "Course name can't be null.")
    @NotBlank(message = "Course name can't be blank.")
    @Size(min = 3, message = "Course name should have at least 3 character. ")
    private String courseName;
}
