package com.example.PaginationDemo.Service;

import com.example.PaginationDemo.dto.StudentDto;
import com.example.PaginationDemo.entities.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    StudentDto addStudent(StudentDto studentDto);

    List<StudentDto> getAllStudent();

    Page<Student> findPaginated(int pageNo);

    StudentDto getStudent(Long studentId);

    StudentDto updateStudent(StudentDto studentDto);

    void deleteStudent(Long studentId);
}
