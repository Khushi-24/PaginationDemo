package com.example.PaginationDemo.ServiceImpl;

import com.example.PaginationDemo.CustomException.BadRequestException;
import com.example.PaginationDemo.CustomException.EntityNotFoundException;
import com.example.PaginationDemo.Repository.CourseStudentRepository;
import com.example.PaginationDemo.Repository.StudentRepository;
import com.example.PaginationDemo.Service.StudentService;
import com.example.PaginationDemo.dto.CourseResponseDto;
import com.example.PaginationDemo.dto.StudentDto;
import com.example.PaginationDemo.entities.CourseStudent;
import com.example.PaginationDemo.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final CourseStudentRepository courseStudentRepository;


    @Override
    public List<StudentDto> getAllStudent() {
        List<StudentDto> studentDtoList = studentRepository.findAll().stream().map(student ->{
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(student.getStudentId());
            studentDto.setStudentName(student.getStudentName());
            studentDto.setStudentDivision(student.getStudentDivision());
            studentDto.setStudentAge(student.getStudentAge());

            List<CourseStudent> courseList = courseStudentRepository.getCourseByStudentStudentId(studentDto.getStudentId());
            List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

            courseList.forEach((e) ->{
                CourseResponseDto courseResponseDto = new CourseResponseDto();
                courseResponseDto.setCourseId(e.getCourse().getCourseId());
                courseResponseDto.setCourseName(e.getCourse().getCourseName());
                courseResponseDtoList.add(courseResponseDto);
            });

            studentDto.setCourseResponseDtoList(courseResponseDtoList);
            return studentDto;
        }).collect(Collectors.toList());

        if(studentDtoList.isEmpty()){
            throw  new EntityNotFoundException(HttpStatus.NOT_FOUND, "Student list is empty.");
        }
        else{
            return studentDtoList;
        }
    }

    @Override
    public Page<Student>  findPaginated(int pageNo){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.studentRepository.findAll(pageable);
    }

    @Override
    public StudentDto getStudent(Long studentId) {
        if (studentRepository.existsById(studentId)) {
            Student student = studentRepository.findById(studentId).get();
            StudentDto studentDto = new StudentDto();
            studentDto.setStudentId(student.getStudentId());
            studentDto.setStudentName(student.getStudentName());
            studentDto.setStudentDivision(student.getStudentDivision());
            studentDto.setStudentAge(student.getStudentAge());

            List<CourseStudent> courseList = courseStudentRepository.getCourseByStudentStudentId(studentId);
            List<CourseResponseDto> courseResponseDtoList = new ArrayList<>();

            courseList.forEach((e) ->{
                CourseResponseDto courseResponseDto = new CourseResponseDto();
                courseResponseDto.setCourseId(e.getCourse().getCourseId());
                courseResponseDto.setCourseName(e.getCourse().getCourseName());
                courseResponseDtoList.add(courseResponseDto);
            });

            studentDto.setCourseResponseDtoList(courseResponseDtoList);

            return studentDto;
        }
        else {
            throw new javax.persistence.EntityNotFoundException("Student doesn't exist");
        }
    }

    @Override
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

    @Override
    public StudentDto updateStudent(StudentDto studentDto) {

        if(studentRepository.existsById(studentDto.getStudentId())){
            return addStudent(studentDto);
        }
        else{
            throw new javax.persistence.EntityNotFoundException("Student doesn't exists so can't be updated");
        }

    }

    @Override
    public void deleteStudent(Long studentId) {
        if (studentId != null) {
            studentRepository.findById(studentId).orElseThrow(() -> new javax.persistence.EntityNotFoundException("Student does not exist"));
            if (courseStudentRepository.existsByStudentStudentId(studentId)) {
                courseStudentRepository.deleteByStudentId(studentId);
            }
            studentRepository.deleteStudentById(studentId);
        }else {
            throw  new BadRequestException(HttpStatus.BAD_REQUEST, "Student Id can't be null");
        }
    }

    @Override
    public void deleteStudentFromCourseStudentTable(Long studentId) {
        if (studentId != null) {
            studentRepository.findById(studentId).orElseThrow(() -> new javax.persistence.EntityNotFoundException("Student does not exist"));
            if(courseStudentRepository.existsByStudentStudentId(studentId)) {
                courseStudentRepository.deleteByStudentId(studentId);
            }else {
                throw new javax.persistence.EntityNotFoundException("Student doesn't exist so can't be deleted");
            }

        }
        else{
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "Student Id can't be null");
        }
    }


}
