package com.example.PaginationDemo.Service;

import com.example.PaginationDemo.dto.TeacherDto;
import com.example.PaginationDemo.entities.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeacherService {
    TeacherDto addTeacher(TeacherDto teacher);

    List<TeacherDto> getAllTeacher();

    TeacherDto updateTeacher(TeacherDto teacherDto);

    void deleteTeacher(Long teacherId);

    void deleteTeacherFromCourseTeacher(Long teacherId);

    Page<Teacher> findPaginated(int pageNo);

    TeacherDto getTeacher(Long teacherId);
}
