package com.example.PaginationDemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "course")
@NoArgsConstructor
@Getter
@Setter
public class Course
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long courseId;

    @Column
    private String courseName;

    @Column
    private String courseDuration;

    @Column
    private String courseDescription;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    private Set<CourseStudent> courseStudentSet = new HashSet<>();

}
