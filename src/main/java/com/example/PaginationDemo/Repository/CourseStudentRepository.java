package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.Course;
import com.example.PaginationDemo.entities.CourseStudent;
import com.example.PaginationDemo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
    boolean existsByCourseAndStudent(Course course, Student student);


    @Query(value = "Select course_student.student_id, student.student_name FROM `course_student`  Inner Join `student` on course_student.student_id = student.student_id WHERE course_id =?1", nativeQuery = true)
    List<Student> getStudentByCourseId(Long courseId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course_student` WHERE course_id = ?1 And student_id =?2", nativeQuery = true)
    void deleteByCourseCourseIdAndStudentStudentId(Long courseId, Long studentId);

    boolean existsByCourseCourseId(Long courseId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course_student` WHERE course_id = ?1", nativeQuery = true)
    void deleteByCourseId(Long courseId);

    boolean existsByStudentStudentId(Long studentId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course_student` WHERE student_id = ?1", nativeQuery = true)
    void deleteByStudentId(Long studentId);
}
