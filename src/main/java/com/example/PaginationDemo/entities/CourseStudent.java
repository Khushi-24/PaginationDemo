package com.example.PaginationDemo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "courseStudent")
@Getter
@Setter
@NoArgsConstructor
public class CourseStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseStudentId;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "studentId", referencedColumnName = "studentId")
    private Student student;

}
