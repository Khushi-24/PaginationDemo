package com.example.PaginationDemo.Controller;

import com.example.PaginationDemo.Service.CourseService;
import com.example.PaginationDemo.dto.CourseDto;
import com.example.PaginationDemo.dto.StudentDto;
import com.example.PaginationDemo.entities.Course;
import com.example.PaginationDemo.entities.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourses(@RequestBody CourseDto courseDto){

        CourseDto addCourse= courseService.addCourse(courseDto);
        return new ResponseEntity<>(addCourse, HttpStatus.CREATED);

    }

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses(){
        List<CourseDto> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/getAllCourses/{pageNo}")
    public ResponseEntity<?> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        Page<Course> page = courseService.findPaginated(pageNo);
        List<Course> courseList = page.getContent();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/getAllCourseByCourseId/{courseId}")
    public  ResponseEntity<?> getCourse(@PathVariable Long courseId){
        CourseDto dto = courseService.getCourse(courseId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    @PutMapping("/updateCourse")
    public ResponseEntity<CourseDto> updateCourse(@RequestBody CourseDto courseDto){
        CourseDto update_course= courseService.updateCourse(courseDto);
        return new ResponseEntity<>(update_course, HttpStatus.CREATED);
    }

    @DeleteMapping("deleteCourse/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course deleted successfully.");
    }

}