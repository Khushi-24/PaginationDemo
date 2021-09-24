package com.example.PaginationDemo.Repository;

import com.example.PaginationDemo.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM `teacher` WHERE teacher_id = ?1", nativeQuery = true)
    void deleteTeacherById(Long teacherId);
}
