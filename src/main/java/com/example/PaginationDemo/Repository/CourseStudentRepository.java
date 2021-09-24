package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.CourseStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseStudentRepository extends JpaRepository<CourseStudent, Long> {
}
