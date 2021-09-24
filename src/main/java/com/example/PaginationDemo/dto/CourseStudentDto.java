package com.example.PaginationDemo.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseStudentDto {

    //CourseStudent table id
    private Long courseStudentId;

    //Course variables


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


    //Student variables

    private Long studentId;

    @NotNull(message = "Student name can't be null.")
    @NotBlank(message = "Student name can't be blank.")
    @Size(min = 3, message = "Student name should have at least 3 character. ")
    private String studentName;

}
