package com.example.PaginationDemo.Controller;

import com.example.PaginationDemo.CustomException.BadRequestException;
import com.example.PaginationDemo.CustomException.ResetContent;
import com.example.PaginationDemo.Service.CourseService;
import com.example.PaginationDemo.dto.*;
import com.example.PaginationDemo.entities.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/getAllCourses")
    public ResponseEntity<?> getAllCourses(){
        List<CourseDto> courseList = courseService.getAllCourses();
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/getAllCourses/{pageNo}")
    public ResponseEntity<?> findPaginated(@PathVariable(value = "pageNo") int pageNo){
        Page<Course> page = courseService.findPaginated(pageNo);
        List<Course> courseList = page.getContent();
        if(pageNo > page.getTotalPages()){
            throw new ResetContent("Reset Content");
        }
        return new ResponseEntity<>(courseList, HttpStatus.OK);
    }

    @GetMapping("/getCourseByCourseId/{courseId}")
    public  ResponseEntity<?> getCourse(@PathVariable Long courseId){
        CourseDto dto = courseService.getCourse(courseId);
        //List<Student> studentList = courseService
        return new ResponseEntity<>(courseService.getCourse(courseId), HttpStatus.OK);
    }

    @PostMapping("/addCourse")
    public ResponseEntity<?> addCourses(@Valid @RequestBody CourseDto courseDto){

        CourseDto addCourse= courseService.addCourse(courseDto);
        return new ResponseEntity<>(addCourse, HttpStatus.CREATED);

    }

    @PostMapping("/addStudentToCourse/studentId/courseId")
    public ResponseEntity<?> addStudentToCourse(@RequestBody CourseStudentRequestDto request) {
        courseService.addStudentToCourse(request);
        return ResponseEntity.ok("Student added to Course successfully.");
    }

    @PostMapping("/addTeacherToCourse/teacherId/courseId")
    public ResponseEntity<?> addTeacherToCourse(@RequestBody CourseTeacherRequestDto request) {
        courseService.addTeacherToCourse(request);
        return ResponseEntity.ok("Teacher added to Course successfully.");
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

    @DeleteMapping("deleteCourseFromCourseStudentTable/{courseId}")
    public ResponseEntity<?> deleteCourseFromCourseStudentTable(@PathVariable Long courseId){
        courseService.deleteCourseFromCourseStudentTable(courseId);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    @DeleteMapping("deleteCourseFromCourseTeacherTable/{courseId}")
    public ResponseEntity<?> deleteCourseFromCourseTeacherTable(@PathVariable Long courseId){
        courseService.deleteCourseFromCourseTeacherTable(courseId);
        return ResponseEntity.ok("Course deleted successfully.");
    }

    @DeleteMapping("/deleteStudentAndCourseFromCourseStudentTable")
    public ResponseEntity<?> deleteStudentAndCourseFromCourseStudentTable(@RequestBody CourseStudentRequestDto courseStudentRequestDto){
        courseService.deleteStudentAndCourseFromCourseStudentTable(courseStudentRequestDto);
        return ResponseEntity.ok("Student And Course Deleted From CourseStudent table Successfully");
    }

    @DeleteMapping("/deleteTeacherAndCourseFromCourseTeacherTable")
    public ResponseEntity<?> deleteTeacherAndCourseFromCourseTeacherTable(@RequestBody CourseTeacherRequestDto courseTeacherRequestDto){
        courseService.deleteTeacherAndCourseFromCourseTeacherTable(courseTeacherRequestDto);
        return ResponseEntity.ok("Teacher And Course Deleted From CourseTeacher Successfully");
    }
}
