package com.example.PaginationDemo.dto;

import com.example.PaginationDemo.entities.Course;
import com.example.PaginationDemo.entities.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class SingleCourseResponseDto {

    private Course course;
    private List<Student> studentList;
}
