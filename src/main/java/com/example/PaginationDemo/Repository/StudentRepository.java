package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `student` WHERE student_id = ?1", nativeQuery = true)
    void deleteStudentById(Long studentId);
}
