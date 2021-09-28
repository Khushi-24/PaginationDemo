package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.Course;
import com.example.PaginationDemo.entities.CourseStudent;
import com.example.PaginationDemo.entities.CourseTeacher;
import com.example.PaginationDemo.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Long> {
    boolean existsByTeacherTeacherId(Long teacherId);

    @Query(value = "Select * FROM `course_teacher`  Inner Join `teacher` on course_teacher.teacher_id = teacher.teacher_id WHERE course_id =?1", nativeQuery = true)
    List<CourseTeacher> getTeacherByCourseCourseId(Long courseId);

    @Query(value = "Select * FROM `course_teacher`  Inner Join `course` on course_teacher.course_id = course.course_id WHERE teacher_id =?1", nativeQuery = true)
    List<CourseTeacher> getCourseByTeacherTeacherId(Long teacherId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course_teacher` WHERE teacher_id = ?1", nativeQuery = true)
    void deleteByTeacherTeacherId(Long teacherId);

    boolean existsByCourseAndTeacher(Course course, Teacher teacher);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course_teacher` WHERE course_id = ?1 And teacher_id =?2", nativeQuery = true)
    void deleteByCourseCourseIdAndTeacherTeacherId(Long courseId, Long teacherId);

    boolean existsByCourseCourseId(Long courseId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course_teacher` WHERE course_id = ?1", nativeQuery = true)
    void deleteByCourseId(Long courseId);
}
