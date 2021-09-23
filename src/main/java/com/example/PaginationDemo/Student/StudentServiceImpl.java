package com.example.PaginationDemo.Student;

import com.example.PaginationDemo.CustomException.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl {

    private final StudentRepository studentRepository;

    public StudentDto addStudent(StudentDto studentDto) {
        Student student = new Student();
        student.setStudentId(studentDto.getStudentId());
        student.setStudentName(studentDto.getStudentName());
        student.setStudentDivision(studentDto.getStudentDivision());
        student.setStudentAge(studentDto.getStudentAge());
        Student savedStudent =studentRepository.save(student);

        StudentDto studentResponse = new StudentDto();
        studentResponse.setStudentId(savedStudent.getStudentId());
        studentResponse.setStudentName(savedStudent.getStudentName());
        studentResponse.setStudentDivision(savedStudent.getStudentDivision());
        studentResponse.setStudentAge(savedStudent.getStudentAge());
        return studentResponse;
    }

    public List<StudentDto> getAllStudent() {
        List<StudentDto> studentDtoList = studentRepository.findAll().stream().map(student ->{
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(student.getStudentId());
            studentDto.setStudentName(student.getStudentName());
            studentDto.setStudentDivision(student.getStudentDivision());
            studentDto.setStudentAge(student.getStudentAge());
            return studentDto;
        }).collect(Collectors.toList());

        if(studentDtoList.isEmpty()){
            throw  new BadRequestException("Student list is empty.");
        }
        else{
            return studentDtoList;
        }
    }

    public Page<Student>  findPaginated(int pageNo){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.studentRepository.findAll(pageable);
    }
}
