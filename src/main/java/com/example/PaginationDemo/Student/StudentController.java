package com.example.PaginationDemo.Student;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentServiceImpl studentService;

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.addStudent(studentDto);
        return new ResponseEntity<StudentDto>(dto,HttpStatus.CREATED);
    }

    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudents(){
        List<StudentDto> dto = studentService.getAllStudent();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getAllStudent/{pageNo}")
    public List<Student> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        Page<Student> page = studentService.findPaginated(pageNo);
        List<Student> listStudent = page.getContent();
        return  listStudent;
    }
}
