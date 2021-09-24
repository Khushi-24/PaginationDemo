package com.example.PaginationDemo.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "CourseTeacher")
@Getter
@Setter
@NoArgsConstructor
public class CourseTeacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseTeacherId;

    @ManyToOne
    @JoinColumn(name = "courseId", referencedColumnName = "courseId")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "teacherId")
    private Teacher teacher;

    public CourseTeacher(Teacher teacher, Course course){
        this.teacher = teacher;
        this.course = course;
    }
}
