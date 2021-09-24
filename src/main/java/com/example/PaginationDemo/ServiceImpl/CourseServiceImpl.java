package com.example.PaginationDemo.ServiceImpl;

import com.example.PaginationDemo.CustomException.BadRequestException;
import com.example.PaginationDemo.CustomException.NoRecordFoundException;
import com.example.PaginationDemo.Repository.CourseRepository;
import com.example.PaginationDemo.Repository.CourseStudentRepository;
import com.example.PaginationDemo.Repository.StudentRepository;
import com.example.PaginationDemo.Service.CourseService;
import com.example.PaginationDemo.dto.CourseDto;
import com.example.PaginationDemo.dto.CourseStudentRequestDto;
import com.example.PaginationDemo.entities.Course;
import com.example.PaginationDemo.entities.CourseStudent;
import com.example.PaginationDemo.entities.Student;
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

    private final StudentRepository studentRepository;

    private final CourseStudentRepository courseStudentRepository;

//    private final MapperFacade mapper;
//
//    public CourseServiceImpl(MapperFactory mapperFactory) {
//        mapper = mapperFactory.getMapperFacade();
//    }

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
    public Page<Course> findPaginated(int pageNo) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.courseRepository.findAll(pageable);
    }

    @Override
    public CourseDto getCourse(Long courseId) {


        if(courseRepository.existsById(courseId)){
            Course course = courseRepository.getOne(courseId);
            CourseDto courseDto = new CourseDto();
            courseDto.setCourseId(course.getCourseId());
            courseDto.setCourseName(course.getCourseName());
            courseDto.setCourseDuration(course.getCourseDuration());
            courseDto.setCourseDescription(course.getCourseDescription());
            List<Student> studentList = courseStudentRepository.getStudentByCourseId(courseDto.getCourseId());
            courseDto.setStudentList(studentList);
//            getList(studentList);
//            List<Student> resultantStudentList= new ArrayList<>();
//            for(Student student : studentList ){
//                resultantStudentList.add(student);
//            }
//            return (courseDto ,resultantStudentList);
            return courseDto;
        }
        else{
            throw new EntityNotFoundException("Course doesn't exist");
        }

    }


    @Override
    public void addStudentToCourse(CourseStudentRequestDto request) {
        if(request.getStudentId() == null || request.getCourseId() == null){
            if(request.getStudentId() == null){
                throw  new BadRequestException("Student Id can't be null");
            }
            if(request.getCourseId() == null){
                throw  new BadRequestException("Course Id can't be null");
            }
        }
        else{
            Student student = studentRepository.findById(request.getStudentId()).orElseThrow(() -> new EntityNotFoundException("Student does not exist."));
            Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new EntityNotFoundException("Course does not exist"));

            CourseStudent courseStudent = new CourseStudent(student, course);
            if(!courseStudentRepository.existsByCourseAndStudent(course, student)){
                courseStudentRepository.save(courseStudent);
            }
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
        deleteCourseFromCourseStudentTable(courseId);
//        Course course = courseRepository.getById(courseId);
//        courseRepository.delete(course);
        courseRepository.deleteCourseById(courseId);
    }


    @Override
    public void deleteStudentAndCourseFromCourseStudentTable(CourseStudentRequestDto courseStudentRequestDto) {
        if(courseStudentRequestDto.getStudentId() == null || courseStudentRequestDto.getCourseId() == null){
            if(courseStudentRequestDto.getStudentId() == null){
                throw  new BadRequestException("Student Id can't be null");
            }
            if(courseStudentRequestDto.getCourseId() == null){
                throw  new BadRequestException("Course Id can't be null");
            }
        }
        else{
            Student student = studentRepository.findById(courseStudentRequestDto.getStudentId()).orElseThrow(() -> new EntityNotFoundException("Student does not exist."));
            Course course = courseRepository.findById(courseStudentRequestDto.getCourseId()).orElseThrow(() -> new EntityNotFoundException("Course does not exist"));

            if(courseStudentRepository.existsByCourseAndStudent(course, student)){
                courseStudentRepository.deleteByCourseCourseIdAndStudentStudentId(courseStudentRequestDto.getCourseId(), courseStudentRequestDto.getStudentId());
            }
            else{
                throw new NoRecordFoundException("No such entry exists");
            }
        }
    }

    @Override
    public void deleteCourseFromCourseStudentTable(Long courseId) {
        if (courseId != null) {
            courseRepository.findById(courseId).orElseThrow(() -> new EntityNotFoundException("Course does not exist"));
            if(courseStudentRepository.existsByCourseCourseId(courseId)) {
                courseStudentRepository.deleteByCourseId(courseId);
            }else {
                throw new EntityNotFoundException("Course doesn't exist so can't be deleted");
            }
        }
        else{
            throw new BadRequestException("Course Id can't be null");
        }
    }


}