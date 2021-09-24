package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CourseTeacherDto {

    private Long courseTeacherId;

    @NotNull(message = "Course id can't be null.")
    @NotBlank(message = "Course id can't be blank.")
    private Long courseId;


    @NotNull(message = "Course name can't be null.")
    @NotBlank(message = "Course name can't be blank.")
    @Size(min = 3, message = "Course name should have at least 3 character. ")
    private String courseName;


    @NotNull(message = "Course Duration can't be null.")
    @NotBlank(message = "Course Duration can't be blank.")
    private String courseDuration;


    @NotNull(message = "Course Description can't be null.")
    @NotBlank(message = "Course Description can't be blank.")
    private String courseDescription;

    @NotNull(message = "Teacher id can't be null.")
    @NotBlank(message = "Teacher id can't be blank.")
    private Long teacherId;

    @NotNull(message = "Teacher name can't be null.")
    @NotBlank(message = "Teacher name can't be blank.")
    private String teacherName;
}
