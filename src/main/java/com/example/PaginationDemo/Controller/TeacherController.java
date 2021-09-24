package com.example.PaginationDemo.Controller;

import com.example.PaginationDemo.Service.TeacherService;
import com.example.PaginationDemo.dto.TeacherDto;
import com.example.PaginationDemo.entities.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/getAllTeacher")
    public ResponseEntity<List<TeacherDto>> getAllTeacher(){
        List<TeacherDto> teacherList= teacherService.getAllTeacher();
        return  new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @GetMapping("/getAllTeacher/{pageNo}")
    public ResponseEntity<?> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        Page<Teacher> page = teacherService.findPaginated(pageNo);
        List<Teacher> listTeacher = page.getContent();
        return new ResponseEntity<>(listTeacher, HttpStatus.OK);
    }

    @GetMapping("/getTeacher/{teacherId}")
    public  ResponseEntity<?> getTeacher(@PathVariable Long teacherId){
        TeacherDto dto = teacherService.getTeacher(teacherId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/addTeacher")
    public ResponseEntity<TeacherDto> addTeacher(@RequestBody TeacherDto teacher){
        TeacherDto teacherDto= teacherService.addTeacher(teacher);
        return new ResponseEntity<>(teacherDto, HttpStatus.CREATED);
    }

    @PutMapping("/updateTeacher")
    public ResponseEntity<TeacherDto> updateTeacher(@RequestBody TeacherDto teacherDto){
        TeacherDto update = teacherService.updateTeacher(teacherDto);
        return new ResponseEntity<>(update, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteTeacher/{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable("teacherId") Long teacherId){
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok("Teacher deleted Successfully");
    }

    @DeleteMapping("/deleteTeacherFromCourseTeacher/{teacherId}")
    public ResponseEntity<?> deleteTeacherFromCourseTeacher(@PathVariable("teacherId") Long teacherId){
        teacherService.deleteTeacherFromCourseTeacher(teacherId);
        return ResponseEntity.ok("Teacher deleted Successfully");
    }

}
