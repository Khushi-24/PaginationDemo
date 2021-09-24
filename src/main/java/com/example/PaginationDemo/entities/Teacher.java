package com.example.PaginationDemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "teacher")
@Getter
@Setter
@NoArgsConstructor

public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @Column
    private String teacherName;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private Set<CourseTeacher> courseTeacherSet = new HashSet<>();

}