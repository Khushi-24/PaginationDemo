package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.Course;
import com.example.PaginationDemo.entities.CourseTeacher;
import com.example.PaginationDemo.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CourseTeacherRepository extends JpaRepository<CourseTeacher, Long> {
    boolean existsByTeacherTeacherId(Long teacherId);

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
