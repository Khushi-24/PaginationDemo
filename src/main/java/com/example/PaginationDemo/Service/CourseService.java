package com.example.PaginationDemo.Service;

import com.example.PaginationDemo.dto.CourseDto;
import com.example.PaginationDemo.dto.CourseStudentRequestDto;
import com.example.PaginationDemo.dto.CourseTeacherDto;
import com.example.PaginationDemo.dto.CourseTeacherRequestDto;
import com.example.PaginationDemo.entities.Course;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CourseService {
    CourseDto addCourse(CourseDto courseDto);

    List<CourseDto> getAllCourses();

    CourseDto updateCourse(CourseDto courseDto);

    void deleteCourse(Long courseId);

    Page<Course> findPaginated(int pageNo);

    CourseDto getCourse(Long courseId);

    void addStudentToCourse(CourseStudentRequestDto request);

    void deleteStudentAndCourseFromCourseStudentTable(CourseStudentRequestDto courseStudentRequestDto);

    void deleteCourseFromCourseStudentTable(Long courseId);

    void deleteTeacherAndCourseFromCourseTeacherTable(CourseTeacherRequestDto courseTeacherRequestDto);

    void deleteCourseFromCourseTeacherTable(Long courseId);
}
