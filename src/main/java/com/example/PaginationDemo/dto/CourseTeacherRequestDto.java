package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CourseTeacherRequestDto {

    @NotNull(message = "Teacher id can't be null.")
    @NotBlank(message = "Teacher id can't be blank.")
    private Long teacherId;

    @NotNull(message = "Course id can't be null.")
    @NotBlank(message = "Course id can't be blank.")
    private Long courseId;
}
