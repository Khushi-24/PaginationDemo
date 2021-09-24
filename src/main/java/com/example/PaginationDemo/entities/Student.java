package com.example.PaginationDemo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name ="student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @Column
    private String studentName;

    @Column
    private String studentDivision;

    @Column
    private Integer studentAge;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private Set<CourseStudent> courseStudentSet = new HashSet<>();

}
