package com.example.PaginationDemo.ServiceImpl;

import com.example.PaginationDemo.CustomException.NoRecordFoundException;
import com.example.PaginationDemo.Repository.CourseRepository;
import com.example.PaginationDemo.Service.CourseService;
import com.example.PaginationDemo.dto.CourseDto;
import com.example.PaginationDemo.entities.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public CourseDto addCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());
        course.setCourseDescription(courseDto.getCourseDescription());
        course.setCourseDuration(courseDto.getCourseDuration());
        Course savedStudent =courseRepository.save(course);

        CourseDto courseResponse = new CourseDto();
        courseResponse.setCourseId(savedStudent.getCourseId());
        courseResponse.setCourseName(savedStudent.getCourseName());
        courseResponse.setCourseDescription(savedStudent.getCourseDescription());
        courseResponse.setCourseDuration(savedStudent.getCourseDuration());
        return courseResponse;

    }

    @Override
    public List<CourseDto> getAllCourses() {
        List<CourseDto> courseList = courseRepository.findAll().stream().map(course ->{
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseDuration(course.getCourseDuration());
            courseDto.setCourseDescription(course.getCourseDescription());
            return courseDto;
        }).collect(Collectors.toList());
        if(courseList.isEmpty()){
            throw  new NoRecordFoundException("List is empty.");
        }
        else {
            return courseList;
        }
    }

    @Override
    public CourseDto updateCourse(CourseDto courseDto) {
        if(courseRepository.existsById(courseDto.getCourseId())){
            return addCourse(courseDto);
        }
        else{
            throw new EntityNotFoundException("Course doesn't exists so can't be updated");
        }
    }

    @Override
    public void deleteCourse(Long courseId) {
        if(courseRepository.existsById(courseId)){
            Course course = courseRepository.getOne(courseId);
            courseRepository.delete(course);
        }
        else {
            throw new EntityNotFoundException("Course doesn't exist so can't be deleted");
        }
    }

    @Override
    public Page<Course> findPaginated(int pageNo) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.courseRepository.findAll(pageable);
    }

    @Override
    public CourseDto getCourse(Long courseId) {

        if(courseRepository.existsById(courseId)){
            Course course = courseRepository.findById(courseId).get();
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseDuration(course.getCourseDuration());
            courseDto.setCourseDescription(course.getCourseDescription());
            return courseDto;
        }
        else{
            throw new EntityNotFoundException("Course doesn't exist");
        }

    }


}