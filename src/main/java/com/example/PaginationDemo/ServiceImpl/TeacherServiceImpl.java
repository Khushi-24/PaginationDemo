package com.example.PaginationDemo.ServiceImpl;

import com.example.PaginationDemo.CustomException.BadRequestException;
import com.example.PaginationDemo.CustomException.NoRecordFoundException;
import com.example.PaginationDemo.Repository.CourseTeacherRepository;
import com.example.PaginationDemo.Repository.TeacherRepository;
import com.example.PaginationDemo.Service.TeacherService;
import com.example.PaginationDemo.dto.TeacherDto;
import com.example.PaginationDemo.entities.Teacher;
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
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseTeacherRepository courseTeacherRepository;

    @Override
    public List<TeacherDto> getAllTeacher() {
        List<TeacherDto> teacherDtoList= teacherRepository.findAll().stream().map(teacher ->{
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.setTeacherId(teacher.getTeacherId());
            teacherDto.setTeacherName(teacher.getTeacherName());
            return teacherDto;
        }).collect(Collectors.toList());
        if(teacherDtoList.isEmpty()){
            throw new NoRecordFoundException("Teacher list is empty");
        }
        else {
            return teacherDtoList;
        }
    }

    @Override
    public Page<Teacher> findPaginated(int pageNo) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return this.teacherRepository.findAll(pageable);
    }

    @Override
    public TeacherDto getTeacher(Long teacherId) {
        if (teacherRepository.existsById(teacherId)) {
            Teacher teacher = teacherRepository.findById(teacherId).get();
            TeacherDto teacherDto = new TeacherDto();
            teacherDto.setTeacherId(teacher.getTeacherId());
            teacherDto.setTeacherName(teacher.getTeacherName());
            return teacherDto;
        }
        else {
            throw new EntityNotFoundException("Teacher doesn't exist");
        }
    }

    @Override
    public TeacherDto addTeacher(TeacherDto teacherdto) {
        Teacher teacher = new Teacher();
        teacher.setTeacherId(teacherdto.getTeacherId());
        teacher.setTeacherName(teacherdto.getTeacherName());

        Teacher savedTeacher = teacherRepository.save(teacher);

        TeacherDto teacherResponse = new TeacherDto();
        teacherResponse.setTeacherId(savedTeacher.getTeacherId());
        teacherResponse.setTeacherName(savedTeacher.getTeacherName());
        return teacherResponse;

    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto) {
        if(teacherRepository.existsById(teacherDto.getTeacherId())){
            return addTeacher(teacherDto);
        }
        else{
            throw new EntityNotFoundException("Teacher doesn't exists so can't be updated");
        }
    }

    @Override
    public void deleteTeacher(Long teacherId) {
        deleteTeacherFromCourseTeacher(teacherId);
        teacherRepository.deleteTeacherById(teacherId);
    }

    @Override
    public void deleteTeacherFromCourseTeacher(Long teacherId) {
        if (teacherId != null) {
            teacherRepository.findById(teacherId).orElseThrow(() -> new EntityNotFoundException("Teacher does not exist"));
            if(courseTeacherRepository.existsByTeacherTeacherId(teacherId)) {
                courseTeacherRepository.deleteByTeacherTeacherId(teacherId);
            }else {
                throw new EntityNotFoundException("Teacher doesn't exist so can't be deleted");
            }

        }
        else{
            throw new BadRequestException("Teacher Id can't be null");
        }
    }




}