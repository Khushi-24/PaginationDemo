package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `course` WHERE course_id = ?1", nativeQuery = true)
    void deleteCourseById(Long courseId);
}
