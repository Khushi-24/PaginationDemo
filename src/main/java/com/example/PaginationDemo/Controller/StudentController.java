package com.example.PaginationDemo.Controller;

import com.example.PaginationDemo.CustomException.ResetContent;
import com.example.PaginationDemo.Service.StudentService;
import com.example.PaginationDemo.dto.StudentDto;
import com.example.PaginationDemo.entities.Student;
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

    private final StudentService studentService;

    @GetMapping("/getAllStudent")
    public ResponseEntity<?> getAllStudents(){
        List<StudentDto> dto = studentService.getAllStudent();
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getAllStudent/{pageNo}")
    public ResponseEntity<?> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        Page<Student> page = studentService.findPaginated(pageNo);
        List<Student> listStudent = page.getContent();
        if(pageNo > page.getTotalPages()){
            throw new ResetContent("Reset Content");
        }
        return new ResponseEntity<>(listStudent, HttpStatus.OK);
    }

    @GetMapping("/getStudent/{studentId}")
    public  ResponseEntity<?> getStudent(@PathVariable Long studentId){
        StudentDto dto = studentService.getStudent(studentId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@Valid @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.addStudent(studentDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.updateStudent(studentDto);
        return new ResponseEntity<>(dto,HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteStudent/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
        return  ResponseEntity.ok("Student deleted successfully.");
    }

    @DeleteMapping("deleteStudentFromCourseStudentTable/{studentId}")
    public ResponseEntity<?> deleteStudentFromCourseStudentTable(@PathVariable Long studentId){
        studentService.deleteStudentFromCourseStudentTable(studentId);
        return ResponseEntity.ok("Student deleted successfully.");
    }


}
