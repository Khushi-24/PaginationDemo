package com.example.PaginationDemo.dto;

import com.example.PaginationDemo.entities.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CourseDto {

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

    private List<Student> studentList;
}
